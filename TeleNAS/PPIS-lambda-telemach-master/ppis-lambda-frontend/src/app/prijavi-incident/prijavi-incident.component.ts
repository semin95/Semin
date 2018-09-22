import {Component, OnInit} from '@angular/core';

import {Incident_Service} from './incident.service';
import {Incident} from 'models/Incident';

@Component({
  providers: [Incident_Service],
  selector: 'app-prijavi-incident',
  templateUrl: './prijavi-incident.component.html',
  styleUrls: ['./prijavi-incident.component.css']
})
export class PrijaviIncidentComponent implements OnInit {

  incidenti: Incident[];
  errorMessage: string;
  bookName: string;
  incident = new Incident();


  constructor(private incidentService: Incident_Service) {
  }

  ngOnInit() {
  }

  /*addIncident(): void {
    this.incidentService.addIncidentWithObservable(this.incident)
      .subscribe(incident => {
          this.bookName = incident.name;
        },
        error => this.errorMessage = <any>error);
  }*/

}
