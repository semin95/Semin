import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Request} from 'models/Request';

@Injectable({
  providedIn: 'root'
})
export class ZahtjevService {

  public API = '//localhost:9090';
  public ZAHTJEV_API = this.API + '/request';

  constructor(private http: HttpClient) {
  }

  getAllRequests(id: number): Observable<any> {
    return this.http.get(this.ZAHTJEV_API + '/user/' + id);
  }

  createRequest(request: Request): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    console.log(request);
    return this.http.post<Request>(this.ZAHTJEV_API, request, httpOptions);
  }

  createSoloRequest(request: Request): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    console.log(request);
    return this.http.post<Request>(this.ZAHTJEV_API, request, httpOptions);
  }

  deleteRequest(id: number): Observable<any> {
    console.log(this.ZAHTJEV_API + '/' + id);
    return this.http.delete(this.ZAHTJEV_API + '/' + id);
  }
}
