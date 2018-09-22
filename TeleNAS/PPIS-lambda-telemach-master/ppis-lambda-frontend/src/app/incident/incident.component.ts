import {Component, OnInit} from '@angular/core';
import {IncidentService} from '../shared/incident/incident.service';
import {UserService} from '../shared/user/user.service';
import {Router} from '@angular/router';
import {AuthService} from '../core/auth.service';
import {User} from '../../models/user';
import {Request} from '../../models/Request';


@Component({
  selector: 'app-incident',
  templateUrl: './incident.component.html',
  styleUrls: ['./incident.component.css']
})

export class IncidentComponent implements OnInit {

  incidents1: Array<any>;

  selectedIncident1: any;
  user1: User;
  idOfUser: number;

  constructor(private incidentService: IncidentService, private userService: UserService, private authService: AuthService, private router: Router) {
  }

  ngOnInit() {

    this.userService.getUserByUsername(this.getCurrentUserName()).subscribe(data => {
      this.user1 = data;
      this.idOfUser = data.id;
      this.getAllIncidents();
    });

  }

  getAllIncidents() {
    this.incidentService.getAllIncidentsByUserId(this.idOfUser).subscribe(data => {
      this.incidents1 = data;
    });
  }

  prikaziIncidentDetalje(incident: any) {
    this.selectedIncident1 = incident;
    this.router.navigate(['/incident', this.selectedIncident1.id]);
  }

  izbrisiIncident1(id, index) {
    console.log('deleting incident with id:' + id + '...');
    this.incidentService.deleteIncident(id)
      .subscribe(() => {
        this.incidents1.splice(index, 1);
      });
  }

  getCurrentUserName() {
    return this.authService.getUserName();
  }

  addItems(request: Request): void {
    this.incidents1.push(request);
  }

  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
  }

}
