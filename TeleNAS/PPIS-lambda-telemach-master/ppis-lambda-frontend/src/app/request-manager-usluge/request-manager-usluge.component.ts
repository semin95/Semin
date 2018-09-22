import {Component, OnInit} from '@angular/core';
import {ProductService} from '../shared/product/product.service';
import {Product} from '../../models/Product';
import {AppComponent} from '../app.component';

@Component({
  selector: 'app-request-manager-usluge',
  templateUrl: './request-manager-usluge.component.html',
  styleUrls: ['./request-manager-usluge.component.css']
})
export class RequestManagerUslugeComponent implements OnInit {

  products: Array<Product>;
  odabrani_product: Product = new Product();
  new_product: Product = new Product();

  constructor(private productService: ProductService,
              private appService: AppComponent) {
  }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(data => {
      this.products = data;
    });
  }

  addItems(product: Product): void {
    this.products.push(product);
  }

  deleteProd(id, index) {
    this.productService.deleteProduct(id)
      .subscribe(() => {
        this.products.splice(index, 1);
      });
  }

  onSelect(product): void {
    this.odabrani_product = product;
  }

  odjaviSe(): void {
    this.appService.odjaviSe();
    document.getElementById('close').click();
  }
}
