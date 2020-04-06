import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVolunteeringComponent } from './edit-volunteering.component';

describe('EditVolunteeringComponent', () => {
  let component: EditVolunteeringComponent;
  let fixture: ComponentFixture<EditVolunteeringComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditVolunteeringComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditVolunteeringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
