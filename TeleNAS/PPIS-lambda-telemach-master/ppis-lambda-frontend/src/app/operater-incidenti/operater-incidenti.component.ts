import { Component, OnInit } from '@angular/core';
import {IncidentService} from '../shared/incident/incident.service';
import {Router} from '@angular/router';
import { AuthService } from '../core/auth.service';

@Component({
  selector: 'app-operater-incidenti',
  templateUrl: './operater-incidenti.component.html',
  styleUrls: ['./operater-incidenti.component.css']
})
export class OperaterIncidentiComponent implements OnInit {

  incidents1: Array<any>;
  selectedIncident1: any;
  incident1: any;

  constructor(private incidentService: IncidentService, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.incidentService.getAllIncidents().subscribe(data => {
      this.incidents1 = data;
    });
  }

  prikaziIncidentDetalje1(incident: any) {
    this.selectedIncident1 = incident;
    console.log(this.selectedIncident1.id);
    this.router.navigate(['/incident', this.selectedIncident1.id]);
  }

  izbrisiIncident(incident: any, index) {
    this.selectedIncident1 = incident;
    console.log(this.selectedIncident1.id);
    this.incidentService.deleteIncident(this.selectedIncident1.id)
      .subscribe(() => {
        console.log('Obrisan incident sa ID: ' + incident.id);
        this.incidents1.splice(index, 1);
      });
  }

  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
  }



}