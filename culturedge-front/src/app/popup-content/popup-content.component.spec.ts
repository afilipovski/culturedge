import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { PopupContentComponent } from './popup-content.component';

describe('PopupContentComponent', () => {
  let component: PopupContentComponent;
  let fixture: ComponentFixture<PopupContentComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupContentComponent ],
      imports: [ HttpClientTestingModule, FormsModule ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupContentComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.cityName).toBe('');
    expect(component.placeName).toBe('');
    expect(component.lat).toBeUndefined();
    expect(component.lon).toBeUndefined();
    expect(component.pictureUrl).toBe('');
    expect(component.description).toBe('');
    expect(component.editMode).toBeFalse();
  });

  it('should fetch description on ngAfterContentInit', () => {
    component.placeName = 'Test Place';
    component.ngAfterContentInit();

    const req = httpMock.expectOne('/api/description?name=Test Place');
    expect(req.request.method).toBe('GET');
    req.flush('Test Description');

    expect(component.description).toBe('Test Description');
  });

  it('should upload photo and refresh photo URL', () => {
    const file = new File(['dummy content'], 'example.png', { type: 'image/png' });
    const event = { target: { files: [file] } } as unknown as Event;

    component.placeName = 'Test Place';
    component.onFileChanged(event);

    const req = httpMock.expectOne('/api/photo');
    expect(req.request.method).toBe('POST');
    req.flush('OK');

    expect(component.pictureUrl).toContain('/api/photo?name=Test Place');
  });

  it('should post description on submit', () => {
    component.placeName = 'Test Place';
    component.description = 'New Description';
    component.onSubmit();

    const req = httpMock.expectOne('/api/description');
    expect(req.request.method).toBe('POST');
    req.flush('OK');

    expect(component.editMode).toBeFalse();
  });

  it('should toggle edit mode', () => {
    component.editMode = false;
    const button = fixture.debugElement.query(By.css('button'));
    button.triggerEventHandler('click', null);

    expect(component.editMode).toBeTrue();
  });

  it('should prompt file upload', () => {
    component.fileUpload = {
      get: jasmine.createSpy('get').and.returnValue(null)
    } as any;
    component.promptUploadPhoto();

    expect(component.fileUpload.get).toHaveBeenCalledWith(0);
  });
});
