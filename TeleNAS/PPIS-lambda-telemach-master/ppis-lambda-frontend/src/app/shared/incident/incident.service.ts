import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Incident} from '../../../models/Incident';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable()
export class IncidentService implements StatsService {

  public API = '//localhost:9090';
  public INCIDENT_API = this.API + '/incident';

  constructor(private http: HttpClient) {
  }

  getAllIncidents(): Observable<Incident[]> {
    return this.http.get<Incident[]>(this.INCIDENT_API + '/all');
  }

  getAllIncidentsByUserId(id: number): Observable<any>{

   return this.http.get(this.INCIDENT_API + '/user/' + id);
  }

  getStats(year: number, month: number): Observable<any> {
    return this.http.get(this.INCIDENT_API + '/stats?year=' + year + (month == null ? '' : ('&month=' + month)));
  }

  getIncident(id: number): Observable<any> {
    return this.http.get(this.INCIDENT_API + '/' + id);
  }

  deleteIncident(id: number): Observable<any> {
    return this.http.delete(this.INCIDENT_API + '/' + id);
  }

  createIncident(incident: any): Observable<any> {
    return this.http.post<any>(this.INCIDENT_API, incident, httpOptions); // TODO use Incident model
  }

  updateIncident(incident: any): Observable<any> {
    return this.http.put<any>(this.INCIDENT_API + '/' + incident.id, incident, httpOptions); // TODO use Incident model
  }
}

