import { Component } from '@angular/core';

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

interface TranslationDictionary {
  [key: string]: string;
}

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent {
  constructor (
    private heritageService : HeritageService  
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
      
  }
}
