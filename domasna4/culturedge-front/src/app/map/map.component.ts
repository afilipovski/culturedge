import {AfterViewInit, Component, ComponentFactoryResolver, ViewChild, ViewContainerRef} from '@angular/core';
import {ComponentPortal} from '@angular/cdk/portal';
import {DomPortalOutlet} from '@angular/cdk/portal';

// import 'ol/ol.css'; // Import OpenLayers CSS
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import {ApiService} from '../api.service';
import {IHeritageSite} from '../IHeritageSite';
import VectorSource from 'ol/source/Vector';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import {fromLonLat, toLonLat} from 'ol/proj';
import VectorLayer from 'ol/layer/Vector';
import Popup from 'ol-popup';
import {Coordinate} from 'ol/coordinate';
import {PopupContentComponent} from '../popup-content/popup-content.component';
import {LocationService} from '../geolocation.component';
import {DistanceService} from '../distance.component';

interface TranslationDictionary {
    [key: string]: string;
}

@Component({
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
    userLocation: { latitude: number; longitude: number } | null = null;

    constructor(
        private heritageService: ApiService,
        private componentFactoryResolver: ComponentFactoryResolver,
        private locationService: LocationService,
        private distanceService: DistanceService
    ) {
    }

    sites: IHeritageSite[] = []
    filteredSites: IHeritageSite[] = []

    queryName: string = ''
    queryType: string = ''
    queryCity: string = ''

    vectorSource = new VectorSource()
    map = new Map({
        target: 'mapdiv',
        layers: [
            new TileLayer({
                source: new OSM(),
            }),
        ],
        view: new View({
            center: fromLonLat([21.7, 41.5]),
            zoom: 8,
        }),
    });
    popup = new Popup()

    change() {
        this.filteredSites = this.sites
            .filter(a => a.name.toLowerCase().includes(this.queryName.toLowerCase()))
            .filter(a => !a.city || a.city.toLowerCase().includes(this.queryCity.toLowerCase()))
            .filter(a => {
                let tokens = []
                if (a.historic != null) tokens.push(this.englishToMacedonian[a.historic])
                if (a.tourism != null) tokens.push(this.englishToMacedonian[a.tourism])
                return tokens.findIndex(a => a.toLowerCase().includes(this.queryType.toLowerCase())) != -1

            })


        this.vectorSource.clear();
        this.filteredSites.forEach(site => {
            const marker = new Feature({
                geometry: new Point(fromLonLat([site.lon, site.lat])),
            });
            marker.set("name", site.name)
            marker.set("city", site.city)
            this.vectorSource.addFeature(marker);
        });
    }

    englishToMacedonian: TranslationDictionary = {
        'monument': 'Споменик',
        'city_gate': 'Градска порта',
        'ruins': 'Рушевина',
        'archaeological_site': 'Археолошко наоѓалиште',
        'memorial': 'Споменик',
        'tomb': 'Гробница',
        'locomotive': 'Локомотива',
        'wayside_shrine': 'Светилиште',
        'castle': 'Тврдина',
        'yes': '',
        'battlefield': 'Бојно поле',
        'aircraft': 'Воздухопловство',
        'attraction': 'Атракција',
        'artwork': 'Уметничко дело'
    };

    ngOnInit() {
        this.locationService.getLocationObservable().subscribe(
            (location: { latitude: number; longitude: number } | null) => {

                this.userLocation = location;
                if (this.userLocation) {
                    this.sortSitesByDistance();
                }
            }
        );

        this.heritageService.getHeritageSites().subscribe(
            a => {
                this.sites = a

                if (this.userLocation) {
                    this.sortSitesByDistance();
                }
                this.change()
            })
        this.map = new Map({
            target: 'mapdiv',
            layers: [
                new TileLayer({
                    source: new OSM(),
                }),
            ],
            view: new View({
                center: fromLonLat([21.7, 41.5]),
                zoom: 8,
            }),
        });
        this.vectorSource = new VectorSource();
        const vectorLayer = new VectorLayer({
            source: this.vectorSource,
        });

        this.map.addLayer(vectorLayer);
        this.map.addOverlay(this.popup);

    }

    private sortSitesByDistance(): void {
        this.sites.sort((a, b) => {
            if (this.userLocation && a.lat && a.lon && b.lat && b.lon) {
                const distanceA = this.distanceService.calculateDistance(
                    this.userLocation.latitude,
                    this.userLocation.longitude,
                    a.lat,
                    a.lon
                );
                const distanceB = this.distanceService.calculateDistance(
                    this.userLocation.latitude,
                    this.userLocation.longitude,
                    b.lat,
                    b.lon
                );

                return distanceA - distanceB;
            }
            return 0;
        });
        this.change();
    }


    @ViewChild('container', {read: ViewContainerRef})
    container!
        :
        ViewContainerRef;

    ngAfterViewInit()
        :
        void {
        this.map.on('click', (evt) => {
            let featureName = ""
            let featureCity: string = ""

            const coords: Coordinate | undefined = this.map.forEachFeatureAtPixel(evt.pixel, d => {
                featureName = d.get("name");
                featureCity = d.get("city");
                return evt.coordinate;
            });

            if (!coords) {
                this.popup.hide();
                return;
            }

            this.popupAtCoords(featureName, coords, featureCity);
        });
    }

    clickedResult: any | null = null;

    sideElementClick(name
                         :
                         string, lonlat
                         :
                         number[], city
                         :
                         string, result
                         :
                         any
    ) {
        this.popupAtCoords(name, fromLonLat(lonlat), city)
        this.clickedResult = result;
    }

    popupAtCoords(placeName
                      :
                      string, coords
                      :
                      Coordinate, cityName
                      :
                      string
    ) {
        const factory = this.componentFactoryResolver.resolveComponentFactory(PopupContentComponent);
        const ref = this.container.createComponent(factory);


        const instance = ref.instance;
        instance.placeName = placeName;
        instance.cityName = cityName;

        this.popup.show(coords!, ref.location.nativeElement);


        coords = toLonLat(coords);
        instance.lat = Math.round(coords[1] * 10000) / 10000;
        instance.lon = Math.round(coords[0] * 10000) / 10000;

    }
}
