import {Component, OnInit} from '@angular/core';
import {ProductService} from '../shared/product/product.service';
import {Router} from '@angular/router';
import {Incident} from './incident';
import {UserService} from '../shared/user/user.service';
import {IncidentService} from '../shared/incident/incident.service';
import {AuthService} from '../core/auth.service';
import {User} from '../../models/User';
import {Product} from '../../models/Product';
import {RequestService} from '../shared/request/request.service';

@Component({
  selector: 'app-mojeusluge',
  templateUrl: './mojeusluge.component.html',
  styleUrls: ['./mojeusluge.component.css']
})

export class MojeuslugeComponent implements OnInit {

  usluge: Array<Product>;

  incident: Incident = {
    name: '',
    description: '',
    wayOfResponse: '',
    status: {
      id: null,
    },
    priority: {
      id: null,
    },
    user: {
      id: null,
    },
    product: {
      id: null,
    }
  };

  modal_naziv: any;
  modal_detalji: any;
  modal_cijena: any;

  user: User;
  selectedProduct: Product;

  wayOfResponseEmail: any;
  wayOfResponseTelefon: any;

  odabraniPrioritet: any;
  prioriteti = [{id: 4, name: 'Mali'}, {id: 3, name: 'Srednji'}, {id: 2, name: 'Znacajan'}, {id: 1, name: 'Hitan'}];


  constructor(private router: Router,
              private productService: ProductService,
              private userService: UserService,
              private authService: AuthService,
              private requestService: RequestService,
              private incidentService: IncidentService) {
  }

  onSelect(product): void {
    this.selectedProduct = product;
  }

  cancelProduct(id, index) {
    this.requestService.getAllRequests().subscribe(data => {
      const cancellingRequest = data.filter(request => request.user.id === this.user.id && request.product.id === id);
      cancellingRequest.forEach(request => {
        this.requestService.deleteRequest(request.id).subscribe(data => {
          // console.log(JSON.stringify(cancellingRequest));
          this.usluge.splice(index, 1);
        });
      });
    });
  }

  getAllProductsByUserId() {
    this.productService.getAllProductsByUserId(this.user.id).subscribe(data => {
      this.usluge = data;
      if (this.usluge[0] == null)
        this.usluge.splice(0, 1);
      console.log(data);
    });
  }

  ngOnInit() {

    this.userService.getUserByUsername(this.getCurrentUserName())
      .subscribe(data => {
        this.user = data;
        this.productService.getAllProductsByUserId(this.user.id).subscribe(data => {
          this.usluge = data;
        });
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
