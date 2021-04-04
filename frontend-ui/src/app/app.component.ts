import {Component} from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {LineItem} from './store/models/line-item.model';

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

  constructor(private store: Store<{ shopping: any }>) {
    this.lineItems$ = store.select('shopping', 'cart', 'lineItems');
    this.netTotal$ = store.select('shopping', 'cart', 'netTotal');

    /*
    Here we wail until the next change detection lifecycle is called
     */
    setTimeout(() => {
      this.loadingProducts$ = this.store.select('shopping', 'products', 'loading');
      this.productsError$ = this.store.select('shopping', 'products', 'errorMessage');

      this.loadingCart$ = this.store.select('shopping', 'cart', 'loading');
      this.cartError$ = this.store.select('shopping', 'cart', 'errorMessage');

    });

  }
}
