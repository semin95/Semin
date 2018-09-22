import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PriorityService} from '../../priority/priority.service';
import {Status} from '../../../../models/Status';
import {AuthService} from '../../../core/auth.service';
import {User} from '../../../../models/User';
import {WayOfSubmissionEnum} from '../../../../models/WayOfSubmissionEnum';
import {Prioritet} from '../../../../models/Prioritet';
import {WayOfResponseEnum} from '../../../../models/WayOfResponseEnum';
import {ActivatedRoute} from '@angular/router';
import {IncidentService} from '../../incident/incident.service';
import {FeedbackIncidentService} from '../../feedbackIncident/feedback-incident.service';
import {UserService} from '../../user/user.service';
import {StatusService} from '../../status/status.service';
import {Incident} from '../../../../models/Incident';
import {Department} from '../../../../models/Department';
import {DepartmentService} from '../../department/department.service';
import {MatSnackBar} from '@angular/material';
import {Product} from '../../../../models/Product';

const ACTION_IDS = {CREATE: 'createIncident', EDIT: 'editIncident', SHOW_DETAILS: 'showDetails'};

@Component({
  selector: 'app-edit-incident',
  templateUrl: './edit-incident.component.html',
  styleUrls: ['./edit-incident.component.css']
})
export class EditIncidentComponent implements OnInit {
  @Input() id: String = 'editIncident';
  @Input() incident: Incident = new Incident();
  @Input() product: Product;
  @Output('create') createdEmitter = new EventEmitter<Incident>();
  @Output('change') changedEmitter = new EventEmitter<Incident>();

  title: String = 'Izmijeni incident';
  comment: String;
  currentUser: User;
  priorities: Array<Prioritet>;
  statuses: Array<Status>;
  departments: Array<Department>;
  defaultDepartment: Department; // todo select/option for departments
  wayOfSubmissions = WayOfSubmissionEnum;
  wayOfResponses = WayOfResponseEnum;
  disabled: boolean;

  constructor(private incidentService: IncidentService,
              private router: ActivatedRoute,
              private feedbackIncidentService: FeedbackIncidentService,
              private priorityService: PriorityService,
              private statusService: StatusService,
              private departmentService: DepartmentService,
              private userService: UserService,
              private alert: MatSnackBar,
              public authService: AuthService) {
  }

  ngOnInit() {
    this.getPriorities();
    this.getStatuses();
    this.getDepartments();
    this.getCurrentUser();
    switch (this.id) {
      case ACTION_IDS.CREATE:
        this.title = 'Prijavi incident';
        break;
      case ACTION_IDS.SHOW_DETAILS:
        this.title = 'Detalji zahtjeva';
        this.disabled = true;
        break;
      // case ACTION_IDS.EDIT_REQUEST: // default
    }
  }

  getPriorities(): void {
    this.priorityService.getAllPriorities().subscribe(data => {
      this.priorities = data;
    });
  }

  getStatuses(): void {
    this.statusService.getAllStatuses().subscribe(data => {
      this.statuses = data;
    });
  }

  getDepartments(): void {
    this.departmentService.getAllDepartments().subscribe(data => {
      this.departments = data;
      this.defaultDepartment = this.departments.find(department => department.id === 1);
    });
  }

  getCurrentUser(): void {
    this.userService.getUserByUsername(this.authService.getUserName()).subscribe(data => {
      this.currentUser = data;
    });
  }

  editIncident(): void {
    if (this.incident && this.incident.id) {
      this.updateIncident();
    } else {
      this.createIncident();
    }
  }

  createIncident(): void {
    this.incident.date = new Date();
    this.incident.user = this.currentUser;
    this.incident.product = this.product;
    this.incident.department = this.defaultDepartment;
    if (!this.authService.isIncidentManager()) {
      this.incident.adminPriority = this.incident.userPriority;
      this.incident.status = this.statuses.find(status => status.id === 1); // status Podnesen
    }
    this.incidentService.createIncident(this.incident).subscribe(data => {
      this.incident = data;
      this.createdEmitter.emit(this.incident);
      console.log(JSON.stringify(this.incident));
      this.incident = new Incident();
      this.alert.open('Incident uspješno prijavljen.', null, {duration: 3000});
    });
  }

  updateIncident(): void {
    this.incidentService.updateIncident(this.incident).subscribe(data => {
      this.incident = data;
      this.changedEmitter.emit(this.incident);
      this.alert.open('Incident uspješno izmijenjen.', null, {duration: 3000});
    });

  }

  protected compareByName(o1: any, o2: any): boolean {
    return o1 && o2 && o1.name === o2.name;
  }

  protected keys(object: {}) {
    return Object.keys(object);
  }
}
