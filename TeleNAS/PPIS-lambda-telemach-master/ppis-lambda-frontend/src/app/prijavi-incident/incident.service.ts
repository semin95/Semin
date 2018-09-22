import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Prioritet} from 'models/Prioritet';
import {Status} from 'models/Status';

@Injectable()
export class Incident_Service {
  url = 'http://localhost:9090/incident';
  prioritet = new Prioritet();
  status: Status;

  constructor(private http: HttpClient) {
  }

  /*getIncidentsWithObservable(): Observable<Incident[]> {
    return this.http.get(this.url)
      .pipe(map(this.extractData),
        catchError(this.handleErrorObservable));
  }*/


  /*addIncidentWithObservable(incident: Incident): Observable<Incident> {
    incident.wayOfSubmission = 'aaaaaa';
    incident.wayOfResponse = 'aaaaaa';

    this.status.name = 'U toku';
    incident.status = this.status;

    this.prioritet.name = 'test_prioritet';
    incident.priority = this.prioritet;

    return this.http.post(this.url, incident)
      .pipe(map(this.extractData), catchError(this.handleErrorObservable));
  }*/

  /*getIncidentsWithPromise(): Promise<Incident[]> {
    return this.http.get(this.url).toPromise()
      .then(this.extractData)
      .catch(this.handleErrorPromise);
  }

  addIncidentWithPromise(incident: Incident): Promise<Incident> {
    return this.http.post(this.url, incident).toPromise()
      .then(this.extractData)
      .catch(this.handleErrorPromise);
  }*/

}
