import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {IncidentService} from '../shared/incident/incident.service';
import {FeedbackIncidentService} from '../shared/feedbackIncident/feedback-incident.service';
import {AuthService} from '../core/auth.service';
import {Incident} from '../../models/Incident';
import {PriorityService} from '../shared/priority/priority.service';
import {FeedbackIncident} from '../../models/FeedbackIncident';
import {StatusService} from '../shared/status/status.service';
import {UserService} from '../shared/user/user.service';
import {User} from '../../models/User';
import {MatSnackBar} from '@angular/material';
import {Location} from '@angular/common';

@Component({
  selector: 'app-incident-detalji',
  templateUrl: './incident-detalji.component.html',
  styleUrls: ['./incident-detalji.component.css']
})
export class IncidentDetaljiComponent implements OnInit {

  incident: Incident;
  comment: String;
  currentUser: User;
  feedbackIncidents: Array<FeedbackIncident>;

  constructor(private incidentService: IncidentService,
              private router: ActivatedRoute,
              private feedbackIncidentService: FeedbackIncidentService,
              private priorityService: PriorityService,
              private statusService: StatusService,
              private userService: UserService,
              private alert: MatSnackBar,
              private location: Location,
              public authService: AuthService) {
  }

  ngOnInit() {
    const id = +this.router.snapshot.paramMap.get('id');
    this.getIncident(id);
    this.getFeedbacks(id);
    this.getCurrentUser();
  }

  getIncident(id): void {
    this.incidentService.getIncident(id).subscribe(incident => {
      this.incident = incident;
    });
  }

  getFeedbacks(id): void {
    this.feedbackIncidentService.getFeedbacks(id).subscribe(data => {
      this.feedbackIncidents = data;
    });
  }

  getCurrentUser(): void {
    this.userService.getUserByUsername(this.authService.getUserName()).subscribe(data => {
      this.currentUser = data;
    });
  }

  createFeedback() {
    const feedbackIncident = new FeedbackIncident();
    feedbackIncident.comment = this.comment; // todo validacija forme
    feedbackIncident.date = new Date();
    feedbackIncident.user = this.currentUser;
    feedbackIncident.incident = this.incident;
    this.feedbackIncidentService.createFeedback(feedbackIncident).subscribe(data => {
      this.feedbackIncidents.push(feedbackIncident);
      this.comment = null;
    });
  }

  setAsProblem(): void {
    this.incidentService.deleteIncident(this.incident.id).subscribe(data => {
      this.alert.open('Incident uspješno prebačen u problem.', null, {duration: 3000});
      this.location.back();
    });
  }
}
