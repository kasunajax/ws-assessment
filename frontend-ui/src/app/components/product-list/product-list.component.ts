import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {LineItem} from '../../store/models/line-item.model';
import {Product} from '../../store/models/product.model';
import {Store} from '@ngrx/store';
import {loadProducts, updateCart} from '../../store/actions/shopping.actions';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  lineItems$: Observable<Array<LineItem>>;
  products$: Observable<Array<Product>>;
  loading$: Observable<boolean>;
  error$: Observable<boolean>;
  productsInCart: Array<Product>;
  cart$: Observable<any>;

  constructor(private store: Store<{ shopping: any }>) {}

  ngOnInit(): void {
    this.cart$ = this.store.select('shopping', 'cart');
    this.lineItems$ =  this.store.select('shopping', 'cart', 'lineItems');
    this.products$ =  this.store.select('shopping', 'products', 'list');
    this.loading$ =  this.store.select('shopping', 'products', 'loading');
    this.error$ =  this.store.select('shopping', 'products', 'errorMessage');
    this.refreshProducts();
    this.lineItems$.subscribe(lineItems => {
      this.productsInCart = lineItems.map(lineItem => lineItem.product);
    });
  }

  refreshProducts(): void {
    this.store.dispatch(loadProducts());
  }

  addToCart(product, lineItems): void {
    const item: LineItem = {product, quantity: 1, numberOfCartons: 0, numberOfUnits: 1};
    this.store.dispatch(updateCart({payload: { lineItems, newLineItem: item }}));
  }

  isProductPresent(product): boolean {
    if (this.productsInCart.findIndex(p => p.name === product.name) >= 0) {
      return true;
    }
    return false;
  }
}
