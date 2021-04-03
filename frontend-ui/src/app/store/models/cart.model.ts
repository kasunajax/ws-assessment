import {LineItem} from './line-item.model';

export interface Cart {
  lineItems: Array<LineItem>;
  netTotal: number;
}
