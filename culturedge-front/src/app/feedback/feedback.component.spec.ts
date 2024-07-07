import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms'; 
import { FeedbackComponent } from './feedback.component';

describe('FeedbackComponent', () => {
  let component: FeedbackComponent;
  let fixture: ComponentFixture<FeedbackComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackComponent ],
      imports: [ HttpClientTestingModule, RouterTestingModule, FormsModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackComponent);
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
    expect(component.name).toBe("");
    expect(component.email).toBe("");
    expect(component.message).toBe("");
    expect(component.editMode).toBe(false);
  });

  it('should update selectedFile when onFileChanged is called', () => {
    const file = new File([''], 'test-file.txt');
    const event = { target: { files: [file] } } as any;

    component.onFileChanged(event);

    expect(component.selectedFile).toBe(file);
  });

  it('should post feedback and reset fields', () => {
    component.name = 'John Doe';
    component.email = 'john@example.com';
    component.message = 'This is a test message';

    component.postFeedback();

    const req = httpMock.expectOne('/api/feedback');
    expect(req.request.method).toBe('POST');
    req.flush('Success');

    expect(component.name).toBe("");
    expect(component.email).toBe("");
    expect(component.message).toBe("");
  });

  it('should not post feedback if fields are empty', () => {
    component.name = '';
    component.email = '';
    component.message = '';

    component.postFeedback();

    const requests = httpMock.match({ method: 'POST', url: '/api/feedback' });
    expect(requests.length).toBe(0);
  });
});
