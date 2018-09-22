import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FeedbackRequest } from './feedbackRequest';


@Injectable({
  providedIn: 'root'
})
export class FeedbackRequestService {

  public API = '//localhost:9090';
  public FEEDBACKI_API = this.API + '/feedbackRequest';

  constructor(private http: HttpClient) { }

  getFeedbacks(id): Observable<any> {
    return this.http.get(this.FEEDBACKI_API + '/request/' + id);
  }

  createFeedback(feedback): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    console.log(feedback);
    return this.http.post<FeedbackRequest>(this.FEEDBACKI_API, feedback, httpOptions);
  }

}
