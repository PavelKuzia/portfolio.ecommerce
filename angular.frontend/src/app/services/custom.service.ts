import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './storage.service';
import { map, Observable } from 'rxjs';
import { Address } from '../common/address';
import { url } from 'inspector';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomService {
  constructor(
    private HttpClient: HttpClient,
    private storageService: StorageService
  ) {}

  getAddress(): Observable<Address> {
    const email = this.storageService.getItem<string>('email')!;
    const url = `${environment.API_URL}/api/get/address?email=${email}`;
    return this.HttpClient.get<Address>(url);
  }
}
