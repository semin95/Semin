import { TestBed, inject } from '@angular/core/testing';

import { ZahtjevService } from './zahtjev.service';

describe('ZahtjevService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ZahtjevService]
    });
  });

  it('should be created', inject([ZahtjevService], (service: ZahtjevService) => {
    expect(service).toBeTruthy();
  }));
});
