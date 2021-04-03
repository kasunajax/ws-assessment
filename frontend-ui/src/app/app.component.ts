import { Component } from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {updateCart, loadProducts} from './store/actions/shopping.actions';
import {Product} from './store/models/product.model';
import {LineItem} from './store/models/line-item.model';
import {Cart} from './store/models/cart.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-ui';

  lineItems$: Observable<Array<LineItem>>;
  netTotal$: Observable<number>;
  totalItems: number;
  products$: Observable<Array<Product>>;
  loading$: Observable<boolean>;
  error$: Observable<boolean>;
  productsInCart: Array<Product>;
  cart$: Observable<any>;

  constructor(private store: Store<{ shopping: any }>) {
    this.cart$ = store.select('shopping', 'cart');
    this.lineItems$ = store.select('shopping', 'cart', 'lineItems');
    this.netTotal$ = store.select('shopping', 'cart', 'netTotal');
    this.products$ = store.select('shopping', 'products', 'list');
    this.loading$ = store.select('shopping', 'products', 'loading');
    this.error$ = store.select('shopping', 'products', 'errorMessage');
    this.lineItems$.subscribe(items => this.totalItems = items.length);

  }
}
