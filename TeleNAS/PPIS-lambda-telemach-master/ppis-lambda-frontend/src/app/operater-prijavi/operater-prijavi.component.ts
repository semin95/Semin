import {Component, OnInit} from '@angular/core';
import {IncidentService} from '../shared/incident/incident.service';
import {UserService} from '../shared/user/user.service';
import {Router} from '@angular/router';
import {AuthService} from '../core/auth.service';

import {ZahtjevService} from '../shared/zahtjev/zahtjev.service';
import {Request} from 'models/Request';
import {ProductService} from '../shared/product/product.service';

@Component({
  selector: 'app-operater-prijavi',
  templateUrl: './operater-prijavi.component.html',
  styleUrls: ['./operater-prijavi.component.css']
})
export class OperaterPrijaviComponent implements OnInit {

  zahtjevi: any;
  //novi zahtjev
  request_pnz: Request = new Request();

  wayOfResponseEmailRequest: any;
  wayOfResponseTelefonRequest: any;

  user: any;

  odabraniPrioritet: any;
  prioriteti: any;
  odabraniProduct: any;
  products: any;

  soloZahtjev: boolean;


  constructor(private incidentService: IncidentService, private userService: UserService, private authService: AuthService, private router: Router, private zahtjevService: ZahtjevService, private productService: ProductService) { }

  ngOnInit() {
  }

  podnesiZahtjev_pnz() {
    if (this.wayOfResponseEmailRequest == true) {
      this.request_pnz.wayOfResponse = 'Email';
    }

    if (this.wayOfResponseTelefonRequest == true) {
      this.request_pnz.wayOfResponse = 'Telefon';
    }

    this.request_pnz.status.id = 1;
    this.request_pnz.userPriority.id = this.odabraniPrioritet;
    this.request_pnz.adminPriority.id = this.odabraniPrioritet;
    this.request_pnz.user.id = this.user.id;
    this.request_pnz.product.id = this.odabraniProduct;

    console.log(this.request_pnz);
    this.zahtjevService.createSoloRequest(this.request_pnz).subscribe(data => {
      window.location.reload();
    });
  }

  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
    document.getElementById('close').click();
  }
}
