import { TestBed } from '@angular/core/testing';

import { StandingServiceService } from './standing-service.service';

describe('StandingServiceService', () => {
  let service: StandingServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StandingServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
