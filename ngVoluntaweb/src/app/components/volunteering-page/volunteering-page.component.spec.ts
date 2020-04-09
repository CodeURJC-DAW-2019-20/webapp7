import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteeringPageComponent } from './volunteering-page.component';

describe('VolunteeringPageComponent', () => {
  let component: VolunteeringPageComponent;
  let fixture: ComponentFixture<VolunteeringPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VolunteeringPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VolunteeringPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
