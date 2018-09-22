import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PriorityService} from '../../priority/priority.service';
import {Status} from '../../../../models/Status';
import {AuthService} from '../../../core/auth.service';
import {User} from '../../../../models/User';
import {Prioritet} from '../../../../models/Prioritet';
import {ActivatedRoute} from '@angular/router';
import {RequestService} from '../../request/request.service';
import {FeedbackRequestService} from '../../feedbackRequest/feedback-request.service';
import {UserService} from '../../user/user.service';
import {StatusService} from '../../status/status.service';
import {Request} from '../../../../models/Request';
import {WayOfSubmissionEnum} from '../../../../models/WayOfSubmissionEnum';
import {WayOfResponseEnum} from '../../../../models/WayOfResponseEnum';
import {Product} from '../../../../models/Product';
import {Department} from '../../../../models/Department';
import {DepartmentService} from '../../department/department.service';
import {MatSnackBar} from '@angular/material';

const ACTION_IDS = {CREATE: 'createRequest', EDIT: 'editRequest', SHOW_DETAILS: 'showDetails'};

@Component({
  selector: 'app-edit-request',
  templateUrl: './edit-request.component.html',
  styleUrls: ['./edit-request.component.css']
})
export class EditRequestComponent implements OnInit {
  @Input() id: String = 'editRequest';
  @Input() request: Request = new Request();
  @Input() product: Product;
  @Output('create') createdEmitter = new EventEmitter<Request>();
  @Output('change') changedEmitter = new EventEmitter<Request>();

  title = 'Izmijeni zahtjev';
  comment: String;
  currentUser: User;
  priorities: Array<Prioritet>;
  statuses: Array<Status>;
  departments: Array<Department>;
  defaultDepartment: Department; // todo select/option for departments
  wayOfSubmissions = WayOfSubmissionEnum;
  wayOfResponses = WayOfResponseEnum;
  disabled: boolean;

  constructor(private requestService: RequestService,
              private router: ActivatedRoute,
              private feedbackRequestService: FeedbackRequestService,
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
        this.title = 'Prijavi zahtjev';
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

  editRequest(): void {
    if (this.request && this.request.id) {
      this.updateRequest();
    } else {
      this.createRequest();
    }
  }

  createRequest(): void {
    this.request.date = new Date();
    this.request.user = this.currentUser;
    this.request.product = this.product;
    this.request.department = this.defaultDepartment;
    if (!this.authService.isRequestManager()) {
      this.request.adminPriority = this.request.userPriority;
      this.request.status = this.statuses.find(status => status.id === 1); // status Podnesen
    }
    this.requestService.createRequest(this.request).subscribe(data => {
      this.request = data;
      this.createdEmitter.emit(this.request);
      this.request = new Request();
      this.alert.open('Zahtjev uspješno prijavljen.', null, {duration: 3000});
    });
  }

  updateRequest(): void {
    this.requestService.updateRequest(this.request).subscribe(data => {
      this.request = data;
      this.changedEmitter.emit(this.request);
      this.alert.open('Zahtjev uspješno izmijenjen.', null, {duration: 3000});
    });
  }

  protected compareByName(o1: any, o2: any): boolean {
    return o1 && o2 && o1.name === o2.name;
  }

  protected keys(object: {}) {
    return Object.keys(object);
  }
}
