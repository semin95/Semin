import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FeedbackIncident} from '../../../models/FeedbackIncident';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class FeedbackIncidentService {

  public API = '//localhost:9090';
  public FEEDBACK_API = this.API + '/feedbackIncident';

  constructor(private http: HttpClient) {
  }

  getFeedbacks(id): Observable<any> {
    return this.http.get(this.FEEDBACK_API + '/incident/' + id);
  }

  createFeedback(feedbackIncident: FeedbackIncident): Observable<any> {
    return this.http.post<FeedbackIncident>(this.FEEDBACK_API, feedbackIncident, httpOptions);
  }
}
