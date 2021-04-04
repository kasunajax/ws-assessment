import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private API_BASE = `${environment.urls.api}/shopping`;

  constructor(private http: HttpClient) {}
  getAll(): Observable<any> {
    return this.http.get(`${this.API_BASE}/products`);
  }
  getSummary(summary: any): Observable<any> {
    return this.http.post(`${this.API_BASE}/summary`, summary);
  }
}
