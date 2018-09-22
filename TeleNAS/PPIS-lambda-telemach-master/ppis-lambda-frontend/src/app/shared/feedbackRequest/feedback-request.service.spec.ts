import { TestBed, inject } from '@angular/core/testing';

import { FeedbackRequestService } from './feedback-request.service';

describe('FeedbackRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FeedbackRequestService]
    });
  });

  it('should be created', inject([FeedbackRequestService], (service: FeedbackRequestService) => {
    expect(service).toBeTruthy();
  }));
});
