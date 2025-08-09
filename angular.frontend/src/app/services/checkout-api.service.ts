import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Purchase } from './../common/purchase';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CheckoutAPIService {
  private purchaseURI = `${environment.API_URL}/api/checkout/purchase`;

  constructor(private HttpClient: HttpClient) {}

  public placeOrder(purchase: Purchase): Observable<any> {
    return this.HttpClient.post<Purchase>(this.purchaseURI, purchase);
  }
}
