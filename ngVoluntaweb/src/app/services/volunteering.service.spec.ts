import { TestBed } from '@angular/core/testing';

import { VolunteeringService } from './volunteering.service';

describe('VolunteeringService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VolunteeringService = TestBed.get(VolunteeringService);
    expect(service).toBeTruthy();
  });
});
