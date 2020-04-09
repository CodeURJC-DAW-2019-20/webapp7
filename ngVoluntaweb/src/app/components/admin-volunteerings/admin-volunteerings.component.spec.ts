import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVolunteeringsComponent } from './admin-volunteerings.component';

describe('AdminVolunteeringsComponent', () => {
  let component: AdminVolunteeringsComponent;
  let fixture: ComponentFixture<AdminVolunteeringsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminVolunteeringsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminVolunteeringsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
