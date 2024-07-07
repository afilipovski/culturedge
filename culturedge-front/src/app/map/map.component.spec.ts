import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { ComponentFactoryResolver } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { ApiService } from '../api.service';
import { LocationService } from '../services/geolocation.component';
import { DistanceService } from '../services/distance.component';
import { FilterContext, NameFilter, CityFilter, TypeFilter } from '../services/search.component';
import { PopupContentComponent } from '../popup-content/popup-content.component';
import { MapComponent } from './map.component';
import { FormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { IHeritageSite } from '../IHeritageSite';

describe('MapComponent', () => {
  let component: MapComponent;
  let fixture: ComponentFixture<MapComponent>;
  let mockApiService: any;
  let mockLocationService: any;
  let mockDistanceService: any;
  let mockFilterContext: any;

  const heritageSites: IHeritageSite[] = [
    { name: 'Site 1', lat: 41.5, lon: 21.7, city: 'City 1', historic: 'monument', tourism: 'attraction' },
    { name: 'Site 2', lat: 41.6, lon: 21.8, city: 'City 2', historic: 'ruins', tourism: null }
  ];

  beforeEach(waitForAsync(() => {
    mockApiService = {
      getHeritageSites: jasmine.createSpy('getHeritageSites').and.returnValue(of(heritageSites))
    };

    mockLocationService = {
      getLocationObservable: jasmine.createSpy('getLocationObservable').and.returnValue(of({ latitude: 41.5, longitude: 21.7 }))
    };

    mockDistanceService = {
      calculateDistance: jasmine.createSpy('calculateDistance').and.returnValue(10)
    };

    mockFilterContext = {
      setFilterStrategy: jasmine.createSpy('setFilterStrategy'),
      applyFilter: jasmine.createSpy('applyFilter').and.callFake((sites: IHeritageSite[], query: string) => sites)
    };

    TestBed.configureTestingModule({
      declarations: [MapComponent, PopupContentComponent],
      imports: [HttpClientTestingModule, FormsModule],
      providers: [
        { provide: ApiService, useValue: mockApiService },
        { provide: LocationService, useValue: mockLocationService },
        { provide: DistanceService, useValue: mockDistanceService },
        { provide: FilterContext, useValue: mockFilterContext }
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.userLocation).toEqual({ latitude: 41.5, longitude: 21.7 });
    expect(component.sites.length).toEqual(2);
  });

  it('should set user location on initialization', () => {
    expect(component.userLocation).toEqual({ latitude: 41.5, longitude: 21.7 });
  });

  it('should fetch and filter heritage sites on initialization', () => {
    expect(mockApiService.getHeritageSites).toHaveBeenCalled();
    expect(component.sites.length).toBe(2);
    expect(component.filteredSites.length).toBe(2);
  });
});
