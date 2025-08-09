import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, lastValueFrom, Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { url } from 'inspector';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private storageService: StorageService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const securedEndpoints = [
      `${environment.API_URL}/api/orders`,
      `${environment.API_URL}/api/get/address`,
      `${environment.API_URL}/api/checkout/purchase`,
    ];

    if (securedEndpoints.some((url) => req.urlWithParams.includes(url))) {
      const authToken: string = this.storageService.getItem('token')!;

      const authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + authToken),
      });

      return next.handle(authReq);
    }

    return next.handle(req);
  }
}
