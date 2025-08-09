import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-login-status',
  imports: [NgIf, RouterLink],
  templateUrl: './login-status.component.html',
  styleUrl: './login-status.component.css',
})
export class LoginStatusComponent implements OnInit {
  isAuthenticated: boolean = false;
  userFullName: string = '';

  constructor(
    private authenticationService: AuthenticationService,
    private storageService: StorageService,
    private router: Router,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.authenticationService.authenticated$.subscribe((val) => {
      this.isAuthenticated = val;
      if (val) {
        this.userFullName = this.storageService.getItem('username')!;
      }
    });

    this.authenticationService.fullName$.subscribe(
      (val) => (this.userFullName = val)
    );
  }

  logout() {
    this.authenticationService.authenticated$.next(false);
    this.authenticationService.fullName$.next('');
    this.storageService.removeItem('token');
    this.storageService.removeItem('cartItems');
    this.storageService.removeItem('username');
    this.storageService.removeItem('email');
    this.cartService.clearCartItems();
    this.isAuthenticated = false;
    this.router.navigate(['/products']);
  }
}
