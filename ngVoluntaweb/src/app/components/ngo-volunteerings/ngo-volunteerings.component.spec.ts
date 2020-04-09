import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgoVolunteeringsComponent } from './ngo-volunteerings.component';

describe('NgoVolunteeringsComponent', () => {
  let component: NgoVolunteeringsComponent;
  let fixture: ComponentFixture<NgoVolunteeringsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgoVolunteeringsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgoVolunteeringsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
