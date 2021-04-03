import {Product} from './product.model';

export interface LineItem {
  product: Product;
  quantity: number;
  numberOfCartons: number;
  numberOfUnits: number;
  discounted?: boolean;
  lineItemTotal?: number;
}
