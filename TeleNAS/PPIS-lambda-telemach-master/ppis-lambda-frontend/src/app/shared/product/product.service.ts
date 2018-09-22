import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';

import {Product} from '../../../models/Product';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ProductService {

  public API = '//localhost:9090';
  public PRODUCT_API = this.API + '/product';

  constructor(private http: HttpClient) {
  }

  getAllProductsByUserId(id: number): Observable<any> {
    return this.http.get(this.PRODUCT_API + '/user/' + id);
  }

  getAllProducts(): Observable<any> {
    return this.http.get(this.PRODUCT_API + '/all');
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(this.PRODUCT_API + '/' + id);
  }

  createProduct(product: Product): Observable<any> {
    return this.http.post<Product>(this.PRODUCT_API, product, httpOptions);
  }

  updateProduct(product: Product): Observable<any> {
    return this.http.put<Product>(this.PRODUCT_API + '/' + product.id, product, httpOptions);
  }

  getAllNotProducts(id: number): Observable<any> {
    return this.http.get(this.PRODUCT_API + '/userNot/' + id);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
