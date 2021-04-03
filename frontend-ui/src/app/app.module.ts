import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import {shoppingReducer} from './store/reducers/shopping.reducer';
import { EffectsModule } from '@ngrx/effects';
import {ShoppingEffects} from './store/effects/shopping.effects';
import {HttpClientModule} from '@angular/common/http';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ShoppingCartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot({
      shopping: shoppingReducer
    }, {}),
    EffectsModule.forRoot([
      ShoppingEffects
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
