import { Component } from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {updateCart, loadProducts} from './store/actions/shopping.actions';
import {Product} from './store/models/product.model';
import {LineItem} from './store/models/line-item.model';
import {Cart} from './store/models/cart.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-ui';

  lineItems$: Observable<Array<LineItem>>;
  netTotal$: Observable<number>;

  loadingProducts$: Observable<boolean>;
  productsError$: Observable<string>;

  loadingCart$: Observable<boolean>;
  cartError$: Observable<string>;

  constructor(private store: Store<{ shopping: any }>, private snackBar: MatSnackBar) {
    this.lineItems$ = store.select('shopping', 'cart', 'lineItems');
    this.netTotal$ = store.select('shopping', 'cart', 'netTotal');

    /*
    Here we wail until the next change detection lifecycle is called
     */
    setTimeout(() => {
      this.loadingProducts$ =  this.store.select('shopping', 'products', 'loading');
      this.productsError$ =  this.store.select('shopping', 'products', 'errorMessage');

      this.loadingCart$ =  this.store.select('shopping', 'cart', 'loading');
      this.cartError$ =  this.store.select('shopping', 'cart', 'errorMessage');

      this.productsError$.subscribe((error) => this.openSnackBar(error));
      this.cartError$.subscribe((error) => this.openSnackBar(error));
    });

  }

  openSnackBar(message: string): void {
    if (message) {
      this.snackBar.open(message, 'Dismiss', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
      });
    }

  }
}
