import {Component, OnInit} from '@angular/core';
import {ZahtjevService} from '../shared/zahtjev/zahtjev.service';
import {Request} from '../../models/Request';
import {AuthService} from '../core/auth.service';
import {UserService} from '../shared/user/user.service';
import {ProductService} from '../shared/product/product.service';
import {PriorityService} from '../shared/priority/priority.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {

  zahtjevi: Array<Request>;
  odabrani_zahjev: Request;
  //novi zahtjev
  request: Request = new Request();

  user: any;

  prioriteti: any;
  products: any;

  soloZahtjev: boolean;


  constructor(
    private zahtjevService: ZahtjevService,
    private authService: AuthService,
    private userService: UserService,
    private productService: ProductService,
    private priorityService: PriorityService,
    private router: Router) {
    }

  prikaziDetalje(zahtjev) {
    this.odabrani_zahjev = zahtjev;
    this.odabrani_zahjev.product.name = '';
    if (this.odabrani_zahjev.product.id == null)
      this.soloZahtjev = true;
    else
      this.soloZahtjev = false;
  }

  otkaziZahtjev(zahtjev, index) {
    this.zahtjevService.deleteRequest(zahtjev.id).subscribe(data => {
      this.zahtjevi.splice(index, 1);
    });
  }

  getAllRequestsById() {
    this.zahtjevService.getAllRequests(this.user.id).subscribe(data => {
      this.zahtjevi = data;
    });
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe(data => {
      this.products = data;
    });
  }

  getAllPriorities() {
    this.priorityService.getAllPriorities().subscribe(data => {
      this.prioriteti = data;
    });
  }

  ngOnInit() {
    this.getAllProducts();
    this.getAllPriorities();

    this.userService.getUserByUsername(this.getCurrentUserName()).subscribe(data => {
      this.user = data;
      this.getAllRequestsById();
    });
  }

  addItem(request: Request): void {
    this.zahtjevi.push(request);
  }

  getCurrentUserName() {
    return this.authService.getUserName();
  }

  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
  }

}
