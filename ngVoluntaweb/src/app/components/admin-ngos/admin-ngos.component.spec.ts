import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNgosComponent } from './admin-ngos.component';

describe('AdminNgosComponent', () => {
  let component: AdminNgosComponent;
  let fixture: ComponentFixture<AdminNgosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminNgosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNgosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
