import {createAction, props} from '@ngrx/store';
import {Product} from '../models/product.model';
import {LineItem} from '../models/line-item.model';
import {Cart} from '../models/cart.model';

export const loadProducts = createAction('[Shopping Component] Load Products');
export const loadProductsSuccess = createAction('[Shopping Component] Load Products Success', props<{ payload: Array<Product> }>());
export const loadProductsFailure = createAction('[Shopping Component] Load Products Failure', props<{ payload: string}>());

export const updateCart = createAction('[Shopping Component] Update Cart', props<{ payload: any }>());
export const updateCartSuccess = createAction('[Shopping Component] Update Cart Success', props<{ payload: Cart }>());
export const updateCartFailure = createAction('[Shopping Component] Update Cart Failure', props<{ payload: string}>());

