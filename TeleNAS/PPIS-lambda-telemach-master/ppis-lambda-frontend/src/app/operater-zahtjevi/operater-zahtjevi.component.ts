import {Component, OnInit} from '@angular/core';
import {ZahtjevService} from '../shared/zahtjev/zahtjev.service';
import {Request} from '../../models/Request';
import {AuthService} from '../core/auth.service';
import {UserService} from '../shared/user/user.service';
import {ProductService} from '../shared/product/product.service';
import {PriorityService} from '../shared/priority/priority.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-operater-zahtjevi',
  templateUrl: './operater-zahtjevi.component.html',
  styleUrls: ['./operater-zahtjevi.component.css']
})
export class OperaterZahtjeviComponent implements OnInit {


  zahtjevi: any;
  odabrani_zahjev: any;
  //novi zahtjev
  request: Request = new Request();

  wayOfResponseEmail: any;
  wayOfResponseTelefon: any;
  wayOfResponseEmailRequest: any;
  wayOfResponseTelefonRequest: any;

  user: any;

  odabraniPrioritet: any;
  prioriteti: any;
  odabraniProduct: any;
  products: any;

  soloZahtjev: boolean;


  constructor(
    private zahtjevService: ZahtjevService,
    private authService: AuthService,
    private userService: UserService,
    private productService: ProductService,
    private priorityService: PriorityService,
    private router: Router) {
      this.getAllProducts();
      this.getAllPriorities();
    }

  prikaziDetalje(zahtjev) {
    this.odabrani_zahjev = zahtjev;
    this.odabrani_zahjev.product.name = '';
    if (this.odabrani_zahjev.product.id == null)
      this.soloZahtjev = true;
    else
      this.soloZahtjev = false;
  }

  urediZahtjev(zahtjev) {
    this.odabrani_zahjev = zahtjev;

    this.request.name = this.odabrani_zahjev.name;
    this.request.description = this.odabrani_zahjev.description;
    this.request.wayOfResponse = this.odabrani_zahjev.wayOfResponse;

    if (zahtjev.wayOfResponse == 'Email')
      this.wayOfResponseEmail = true;
    if (zahtjev.wayOfResponse == 'Telefon')
      this.wayOfResponseTelefon = true;
    if (zahtjev.wayOfResponse == 'telephone')
      this.wayOfResponseTelefon = true;
    if (zahtjev.wayOfResponse == 'web application')
      this.wayOfResponseEmail = true;

  }

  urediiZahtjev() {

    if (this.odabrani_zahjev.wayOfResponse == 'Email')
      this.wayOfResponseEmail = true;
    if (this.odabrani_zahjev.wayOfResponse == 'Telefon')
      this.wayOfResponseTelefon = true;

    console.log(this.odabrani_zahjev);
    console.log(this.request);
    console.log(this.odabraniPrioritet);
    //kod za povezivanje sa bekendom
  }

  loadajStranicu(){
    window.location.reload();
  }

  otkaziZahtjev(zahtjev) {
    this.zahtjevService.deleteRequest(zahtjev.id).subscribe(data => {
      console.log('successful');
    });
  }

  podnesiZahtjev() {
    if (this.wayOfResponseEmailRequest == true) {
      this.request.wayOfResponse = 'Email';
    }

    if (this.wayOfResponseTelefonRequest == true) {
      this.request.wayOfResponse = 'Telefon';
    }

    this.request.status.id = 1;
    this.request.userPriority.id = this.odabraniPrioritet;
    this.request.adminPriority.id = this.odabraniPrioritet;
    this.request.user.id = this.user.id;
    this.request.product.id = this.odabraniProduct;

    console.log(this.request);
    this.zahtjevService.createSoloRequest(this.request).subscribe(data => {
      window.location.reload();
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

    this.userService.getUserByUsername(this.getCurrentUserName()).subscribe(data => {
      this.user = data;
      this.getAllRequestsById();
    });
  }

  getCurrentUserName() {
    return this.authService.getUserName();
  }


  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
  }

}
