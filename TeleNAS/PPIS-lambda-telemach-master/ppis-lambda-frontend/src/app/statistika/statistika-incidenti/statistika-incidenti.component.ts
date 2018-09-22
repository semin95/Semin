import {Component} from '@angular/core';
import {StatistikaComponent} from '../statistika.component';
import {IncidentService} from '../../shared/incident/incident.service';

@Component({
  selector: 'app-statistika-incidenti',
  templateUrl: '../statistika.component.html',
  styleUrls: ['../statistika.component.css']
})

export class StatistikaIncidentiComponent extends StatistikaComponent {

  constructor(private incidentService: IncidentService) {
    super(incidentService);
  }

  ngOnInit(): void {
    this.title = 'Generisanje izvještaja za incidente';
  }
}

