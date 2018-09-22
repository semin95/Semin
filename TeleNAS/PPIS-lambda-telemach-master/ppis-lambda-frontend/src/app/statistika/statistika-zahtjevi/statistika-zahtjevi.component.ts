import {Component} from '@angular/core';
import {StatistikaComponent} from '../statistika.component';
import {RequestService} from '../../shared/request/request.service';

@Component({
  selector: 'app-statistika-zahtjevi',
  templateUrl: '../statistika.component.html',
  styleUrls: ['../statistika.component.css']
})
export class StatistikaZahtjeviComponent extends StatistikaComponent {

  constructor(private requestService: RequestService) {
    super(requestService);
  }

  ngOnInit() {
    this.title = 'Generisanje izvještaja za zahtjeve';
  }
}
