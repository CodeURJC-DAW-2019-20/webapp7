import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinedVolunteeringsComponent } from './joined-volunteerings.component';

describe('JoinedVolunteeringsComponent', () => {
  let component: JoinedVolunteeringsComponent;
  let fixture: ComponentFixture<JoinedVolunteeringsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinedVolunteeringsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinedVolunteeringsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
