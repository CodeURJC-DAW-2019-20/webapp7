import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgoSettingsComponent } from './ngo-settings.component';

describe('NgoSettingsComponent', () => {
  let component: NgoSettingsComponent;
  let fixture: ComponentFixture<NgoSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgoSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgoSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
