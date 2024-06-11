import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivacyandtermsComponent } from './privacyandterms.component';

describe('PrivacyandtermsComponent', () => {
  let component: PrivacyandtermsComponent;
  let fixture: ComponentFixture<PrivacyandtermsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrivacyandtermsComponent]
    });
    fixture = TestBed.createComponent(PrivacyandtermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
