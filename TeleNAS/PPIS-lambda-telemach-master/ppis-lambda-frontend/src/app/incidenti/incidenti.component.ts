import {Component, OnInit} from '@angular/core';

import {IncidentService} from '../shared/incident/incident.service';
import {Router} from '@angular/router';
import {Incident} from '../../models/Incident';
import {AppComponent} from '../app.component';
import {MatSnackBar} from '@angular/material';
import * as jsPDF from 'jspdf';


@Component({
  providers: [IncidentService],
  selector: 'app-incidenti',
  templateUrl: './incidenti.component.html',
  styleUrls: ['./incidenti.component.css']
})

export class IncidentiComponent implements OnInit {

  incidents: Array<Incident> = [];
  selectedIncident: Incident;

  // dirty 'n bad
  nalogNaziv: String;
  nalogTekst: String;

  constructor(private incidentService: IncidentService,
              private router: Router,
              private alert: MatSnackBar,
              private appService: AppComponent) {
  }

  ngOnInit() {
    this.loadAllIncidents();
  }

  loadAllIncidents(): void {
    this.incidentService.getAllIncidents()
      .subscribe((res: Incident[]) => {
          for (let i = 0; i < res.length; i++) {
            const copy: Incident = Object.assign({}, res[i]);
            this.incidents.push(copy);
          }
        }
      );
  }

  prikaziIncidentDetalje(incident: Incident) {
    this.selectedIncident = incident;
    console.log(this.selectedIncident.id);
    this.router.navigate(['/incident', this.selectedIncident.id]);
  }

  izbrisiIncident(incident: Incident, index) {
    this.selectedIncident = incident;
    console.log(this.selectedIncident.id);
    this.incidentService.deleteIncident(this.selectedIncident.id)
      .subscribe(() => {
        console.log('Obrisan incident sa ID: ' + incident.id);
        this.incidents.splice(index, 1);
      });
  }

  oznaciIncidentZaNalog(incident: Incident) {
    this.selectedIncident = incident;
  }

  podnesiNalog() {
    this.selectedIncident.radniNalog = this.nalogNaziv + '\n\n\n' + this.nalogTekst;
    this.incidentService.updateIncident(this.selectedIncident).subscribe(
      data => {
        this.alert.open('Nalog kreiran', 'OK', {duration: 4000});
        this.generisiNalog();
      });
  }

  getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
  }

  generisiNalog() {

    const doc = new jsPDF();
    doc.text('Telenash', 100, 10);

    doc.text('Nalog za incidente ', 10, 20);
    doc.line(10, 22, 100, 22); // horizontal line

    doc.text('Naziv i redni broj naloga: ', 10, 35);
    doc.text(this.nalogNaziv, 20, 45);
    // doc.line(10, 48, 100, 48); // horizontal line

    doc.text('Tekst naloga: ', 10, 70);
    doc.text(this.nalogTekst, 20, 80);
    // doc.line(10, 83, 100, 83); // horizontal line

    doc.text('Nalog kreirao: ', 110, 160);
    doc.text(this.appService.getCurrentUserInfo(), 155, 160);
    // doc.line(110, 163, 160, 163); // horizontal line

    const fileName = 'nalog' + this.getRandomInt(1000) + '.pdf';
    doc.save(fileName);
  }

  odjaviSe(): void {
    this.appService.odjaviSe();
    document.getElementById('close').click();
  }
}
