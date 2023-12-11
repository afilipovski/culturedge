import { AfterViewInit, Component, ComponentFactoryResolver, ViewChild, ViewContainerRef } from '@angular/core';
import { ComponentPortal } from '@angular/cdk/portal';
import { DomPortalOutlet } from '@angular/cdk/portal';

// import 'ol/ol.css'; // Import OpenLayers CSS
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { HeritageService } from '../heritage.service';
import { IHeritageSite } from '../IHeritageSite';
import VectorSource from 'ol/source/Vector';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { fromLonLat } from 'ol/proj';
import VectorLayer from 'ol/layer/Vector';
import Popup from 'ol-popup';
import { Coordinate } from 'ol/coordinate';
import { PopupContentComponent } from '../popup-content/popup-content.component';


interface TranslationDictionary {
  [key: string]: string;
}

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  constructor (
    private heritageService : HeritageService,
    private componentFactoryResolver : ComponentFactoryResolver
  ) {}

  sites : IHeritageSite[] = []
  filteredSites : IHeritageSite[] = []

  queryName : string = ''
  queryType : string = ''

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
      this.vectorSource.addFeature(marker);
    });
  }
  
  englishToMacedonian : TranslationDictionary = {
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
    this.heritageService.getHeritageSites().subscribe(
      a => {
      this.sites=a
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

  @ViewChild('container', { read: ViewContainerRef }) container!: ViewContainerRef;

  ngAfterViewInit(): void {
    this.map.on('click', (evt) => {
      const coords : Coordinate | undefined = this.map.forEachFeatureAtPixel(evt.pixel, d => {
        return evt.coordinate;
      });
      const title = 'Popup Title';
      const content = 'Popup Content';

      const factory = this.componentFactoryResolver.resolveComponentFactory(PopupContentComponent);
      const ref = this.container.createComponent(factory);

      this.popup.show(coords!, ref.location.nativeElement);
    });
  }
}
