import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {LineItem} from '../../store/models/line-item.model';
import {Product} from '../../store/models/product.model';
import {Store} from '@ngrx/store';

@Component({
  selector: 'app-shopping-summary',
  templateUrl: './shopping-summary.component.html',
  styleUrls: ['./shopping-summary.component.css']
})
export class ShoppingSummaryComponent implements OnInit {

  lineItems$: Observable<Array<LineItem>>;
  products$: Observable<Array<Product>>;
  loading$: Observable<boolean>;
  error$: Observable<boolean>;
  cart$: Observable<any>;

  constructor(private store: Store<{ shopping: any }>) {}

  ngOnInit(): void {
    this.cart$ = this.store.select('shopping', 'cart');
    this.lineItems$ =  this.store.select('shopping', 'cart', 'lineItems');
    this.products$ =  this.store.select('shopping', 'products', 'list');
    this.loading$ =  this.store.select('shopping', 'cart', 'loading');
    this.error$ =  this.store.select('shopping', 'cart', 'errorMessage');
  }

}
