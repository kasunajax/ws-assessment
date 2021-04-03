import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {LineItem} from '../store/models/line-item.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private API_BASE = 'http://localhost:8080/api/shopping';

  constructor(private http: HttpClient) {}
  getAll(): Observable<any> {
    return this.http.get(`${this.API_BASE}/products`);
  }
  getSummary(summary: any): Observable<any> {
    console.log(summary);
    return this.http.post(`${this.API_BASE}/summary`, summary);
  }
}
