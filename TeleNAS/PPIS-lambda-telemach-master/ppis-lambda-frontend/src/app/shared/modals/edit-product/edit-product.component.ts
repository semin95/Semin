import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../../models/Product';
import {MatSnackBar} from '@angular/material';
import {ProductService} from '../../product/product.service';

const ACTION_IDS = {CREATE: 'createProduct', EDIT: 'editProduct', SHOW_DETAILS: 'showDetails'};

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  @Input() id: String = 'editProduct';
  @Input() product: Product = new Product();
  @Output('create') createdEmitter = new EventEmitter<Product>();
  @Output('change') changedEmitter = new EventEmitter<Product>();

  title = 'Izmijeni uslugu';
  disabled: boolean;

  constructor(private productService: ProductService,
              private alert: MatSnackBar) {
  }

  ngOnInit() {
    switch (this.id) {
      case ACTION_IDS.CREATE:
        this.title = 'Dodaj uslugu';
        break;
      case ACTION_IDS.SHOW_DETAILS:
        this.title = 'Detalji usluge';
        this.disabled = true;
        break;
      // case ACTION_IDS.EDIT_REQUEST: // default
    }
  }

  editProduct(): void {
    if (this.product && this.product.id) {
      this.updateProduct();
    } else {
      this.createProduct();
    }
  }

  createProduct(): void {
    this.productService.createProduct(this.product).subscribe(data => {
      this.product = data;
      this.createdEmitter.emit(this.product);
      console.log(JSON.stringify(this.product));
      this.product = new Product();
      this.alert.open('Usluga uspješno dodana.', null, {duration: 3000});
    });
  }

  updateProduct(): void {
    this.productService.updateProduct(this.product).subscribe(data => {
      this.product = data;
      this.changedEmitter.emit(this.product);
      this.alert.open('Usluga uspješno izmijenjena.', null, {duration: 3000});
    });
  }

  protected compareByName(o1: any, o2: any): boolean {
    return o1 && o2 && o1.name === o2.name;
  }

  protected keys(object: {}) {
    return Object.keys(object);
  }
}
