import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { LineItem } from '../../store/models/line-item.model';
import { Product } from '../../store/models/product.model';
import { Store } from '@ngrx/store';
import { updateCart } from '../../store/actions/shopping.actions';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  lineItems$: Observable<Array<LineItem>>;
  products$: Observable<Array<Product>>;
  loading$: Observable<boolean>;
  error$: Observable<boolean>;
  cart$: Observable<any>;
  cart: any;

  displayedColumns = ['name', 'price', 'discounted', 'lineItemTotal', 'action'];

  constructor(private store: Store<{ shopping: any }>) {}

  ngOnInit(): void {
    this.cart$ = this.store.select('shopping', 'cart');
    this.cart$.subscribe(cart => this.cart = cart);
    this.lineItems$ =  this.store.select('shopping', 'cart', 'lineItems');
    this.products$ =  this.store.select('shopping', 'products', 'list');
    this.loading$ =  this.store.select('shopping', 'cart', 'loading');
    this.error$ =  this.store.select('shopping', 'cart', 'errorMessage');
  }

  setQuantity(product, numberOfCartons, numberOfUnits, changeOfCartons, changeOfUnits): void {

    const newNumberOfCartons = Number(numberOfCartons) + changeOfCartons;
    const newNumberOfUnits = Number(numberOfUnits) + changeOfUnits;

    const item: LineItem = { product, quantity: 1, numberOfCartons: newNumberOfCartons, numberOfUnits: newNumberOfUnits };
    this.store.dispatch(updateCart({payload: { lineItems: this.cart.lineItems, newLineItem: item }}));
  }

  removeFromCart(product, lineItems): void {
    const item: LineItem = { product, quantity: 0, numberOfCartons: 0, numberOfUnits: 0 };
    this.store.dispatch(updateCart({payload: { lineItems, newLineItem: item }}));
  }

  // updateCart(product, numberOfCartons, numberOfUnits, lineItems): void {
  //
  //   const newNumberOfCartons = Number(numberOfCartons);
  //   const newNumberOfUnits = Number(numberOfUnits);
  //
  //   const item: LineItem = { product, quantity: 1, numberOfCartons: newNumberOfCartons, numberOfUnits: newNumberOfUnits };
  //   this.store.dispatch(updateCart({payload: { lineItems, newLineItem: item }}));
  // }

  updateCart(product, value): void {

    const newNumberOfUnits = Number(value);

    const item: LineItem = { product, quantity: 1, numberOfCartons: 0, numberOfUnits: newNumberOfUnits };
    this.store.dispatch(updateCart({payload: { lineItems: this.cart.lineItems, newLineItem: item }}));
  }

}
