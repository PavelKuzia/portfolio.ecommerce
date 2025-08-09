import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private baseUrl = `${environment.API_URL}/auth`;

  public authenticated$ = new BehaviorSubject<boolean>(false);
  public fullName$ = new BehaviorSubject<string>('');

  constructor(
    private HttpClient: HttpClient,
    private storageService: StorageService
  ) {}

  get authenticated(): boolean {
    if (this.storageService.getItem('token')) return true;
    else return this.authenticated$.getValue();
  }

  get authenticatedObservable(): Observable<boolean> {
    return this.authenticated$.asObservable();
  }

  signUp(data: SignUpData): Observable<object> {
    const url: string = `${this.baseUrl}/signup`;
    return this.HttpClient.post(url, data);
  }

  login(data: LoginData): Observable<LoginResponse> {
    const url: string = `${this.baseUrl}/login`;
    return this.HttpClient.post<LoginResponse>(url, data);
  }
}

interface LoginResponse {
  token: string;
  expiresIn: number;
  fullName: string;
}

interface SignUpData {
  username: string;
  email: string;
  password: string;
}

interface LoginData {
  email: string;
  password: string;
}
