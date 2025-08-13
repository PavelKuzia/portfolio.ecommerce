import { NgIf, NgStyle } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-login-status',
  imports: [NgIf, RouterLink, NgStyle],
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

  log_in_out() {
    if (this.isAuthenticated) {
      this.authenticationService.fullName$.next('');
      this.authenticationService.authenticated$.next(false);
      this.storageService.cleanStorage();
      this.cartService.clearCartItems();
      this.isAuthenticated = false;
      this.router.navigate(['/products']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
