import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, mergeMap, catchError } from 'rxjs/operators';
import {ShoppingService} from '../../services/shopping.service';
import {
  loadProducts,
  loadProductsFailure,
  loadProductsSuccess,
  updateCart,
  updateCartFailure,
  updateCartSuccess
} from '../actions/shopping.actions';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable()
export class ShoppingEffects {

  loadProducts$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadProducts),
      mergeMap(() => this.shoppingService.getAll()
        .pipe(
          map(products => loadProductsSuccess({payload: products})),
          catchError((e: HttpErrorResponse) => {
            console.log(e);
            return of(loadProductsFailure({payload: e.message}));
          })
        )
      )
    )
  );
  updateCart$ = createEffect(() =>
    this.actions$.pipe(
      ofType(updateCart),
      mergeMap(({payload}) => this.shoppingService.getSummary(payload)
        .pipe(
          map(summary => updateCartSuccess({payload: summary})),
          catchError((e: HttpErrorResponse) => {
            console.log(e);
            return of(updateCartFailure({payload: e.message}));
          })
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private shoppingService: ShoppingService
  ) {}
}
