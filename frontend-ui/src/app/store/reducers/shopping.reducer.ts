import {ActionReducer, createReducer, on} from '@ngrx/store';
import {
  updateCart, updateCartSuccess, updateCartFailure,
  loadProducts, loadProductsSuccess, loadProductsFailure} from '../actions/shopping.actions';
import {Product} from '../models/product.model';
import {Cart} from '../models/cart.model';

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
  on(loadProductsSuccess, (state, action: any) => ({
    ...state,
    products: {...state.products, loading: false, error: false, errorMessage: null, list: action.payload},
    // cart: {
    //   ...state.cart,
    //   lineItems: action.payload.map(product => ({quantity: 1, product}))}
  }
  )
  ),
  on(loadProductsFailure, (state, action: any) => ({
    ...state, products: {...state.products, loading: false, error: true, errorMessage: action.payload}})
  ),
  on(updateCart, (state) => ({...state, cart: {...state.cart, loading: true}})),
  on(updateCartSuccess, (state, { payload }) => {

    return {
      ...state,
      cart: {
        ...state.cart,
        loading: false,
        error: false,
        lineItems: payload.lineItems,
        netTotal: payload.netTotal
      }
    };
    // const newLineItems = [...state.cart.lineItems];
    // const cart = {...state.cart};
    // newLineItems.push({ product, id: 1, subTotal: 123, quantity: 3 });
    // cart.lineItems = newLineItems;
    // return {...state, cart};
  }),
  on(updateCartFailure, (state, action: any) => ({
    ...state, cart: {...state.cart, loading: false, error: true, errorMessage: action.payload}})
  ),
);

export function shoppingReducer(state, action): any {
  const x = mShoppingReducer(state, action);
  console.log(action);
  console.log(state);
  return x;
}
