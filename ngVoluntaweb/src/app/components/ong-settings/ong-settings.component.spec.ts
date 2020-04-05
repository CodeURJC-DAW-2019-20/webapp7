import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OngSettingsComponent } from './ong-settings.component';

describe('OngSettingsComponent', () => {
  let component: OngSettingsComponent;
  let fixture: ComponentFixture<OngSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OngSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OngSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
