import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../shared/product/product.service';
import {RequestService} from '../shared/request/request.service';
import {UserService} from '../shared/user/user.service';
import {TokenStorage} from '../core/token.storage';
import {AuthService} from '../core/auth.service';
import {HttpClient} from '@angular/common/http';
import {Product} from '../../models/Product';

@Component({
  selector: 'app-pocetna',
  templateUrl: './pocetna.component.html',
  styleUrls: ['./pocetna.component.css']
})

export class PocetnaComponent implements OnInit {

  products: Array<any>;
  user: any;
  idOfUser: number;

  selectedProduct: Product;

  constructor(
    private productService: ProductService,
    private router: Router,
    private router2: ActivatedRoute,
    private requestService: RequestService,
    private http: HttpClient,
    private token: TokenStorage,
    private authService: AuthService,
    private userService: UserService,
  ) {
  }

  ngOnInit() {

    this.userService.getUserByUsername(this.getCurrentUserName()).subscribe(data => {
      this.user = data;
      this.idOfUser = data.id;
      this.productService.getAllNotProducts(this.idOfUser).subscribe(data1 => {
        this.products = data1;
      });
    });

  }

  onSelect(product): void {
    this.selectedProduct = product;
  }

  getCurrentUserName() {
    return this.authService.getUserName();
  }

  getCurrentUserInfo(): string {
    return AuthService.getCurrentUser();
  }

  odjaviSe() {
    AuthService.logOut();
    this.router.navigate(['/telenash']);
    document.getElementById('close').click();
  }

}
