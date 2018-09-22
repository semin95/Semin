import { TestBed, inject } from '@angular/core/testing';

import { FeedbackIncidentService } from './feedback-incident.service';

describe('FeedbackIncidentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FeedbackIncidentService]
    });
  });

  it('should be created', inject([FeedbackIncidentService], (service: FeedbackIncidentService) => {
    expect(service).toBeTruthy();
  }));
});
