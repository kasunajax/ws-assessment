import { createReducer, on } from '@ngrx/store';
import {
  updateCart, updateCartSuccess, updateCartFailure,
  loadProducts, loadProductsSuccess, loadProductsFailure
} from '../actions/shopping.actions';

export const initialState: any = {
  cart: {
    netTotal: 0,
    errorMessage: null,
    loading: false,
    error: false,
    lineItems: []
  },
  products: {
    errorMessage: null,
    loading: false,
    error: false,
    list: []
  }
};

const mShoppingReducer = createReducer(
  initialState,

  on(loadProducts, (state) => ({...state, products: {...state.products, loading: true}})),

  on(loadProductsSuccess, (state, action: any) => (
    {...state, products: {...state.products, loading: false, error: false, errorMessage: null, list: action.payload},
  })),

  on(loadProductsFailure, (state, action: any) => ({
    ...state, products: {...state.products, loading: false, error: true, errorMessage: action.payload}})
  ),

  on(updateCart, (state) => ({...state, cart: {...state.cart, loading: true}})),

  on(updateCartSuccess, (state, { payload }) => (
      {...state, cart: {...state.cart, loading: false, error: false, lineItems: payload.lineItems, netTotal: payload.netTotal}}
  )),

  on(updateCartFailure, (state, action: any) => (
    {...state, cart: {...state.cart, loading: false, error: true, errorMessage: action.payload}}
  )),
);

export function shoppingReducer(state, action): any {
  return mShoppingReducer(state, action);
}
