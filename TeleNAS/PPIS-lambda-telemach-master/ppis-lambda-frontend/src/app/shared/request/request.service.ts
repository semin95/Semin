import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Request} from '../../../models/Request';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class RequestService implements StatsService {

  public API = '//localhost:9090';
  public REQUEST_API = this.API + '/request';

  constructor(private http: HttpClient) {
  }

  createRequest(request: Request): Observable<any> {
    console.log(request);
    return this.http.post<Request>(this.REQUEST_API, request, httpOptions);
  }

  updateRequest(request: any): Observable<any> {
    return this.http.put<any>(this.REQUEST_API + '/' + request.id, request, httpOptions); // TODO use Incident model
  }

  getStats(year: number, month: number): Observable<any> {
    return this.http.get(this.REQUEST_API + '/stats?year=' + year + (month == null ? '' : ('&month=' + month)));
  }

  getRequest(id: number): Observable<any> {
    return this.http.get(this.REQUEST_API + '/' + id);
  }

  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(this.REQUEST_API + '/all');
  }

  deleteRequest(id: number): Observable<any> {
    console.log(this.REQUEST_API + '/' + id);
    return this.http.delete(this.REQUEST_API + '/' + id);
  }
}
