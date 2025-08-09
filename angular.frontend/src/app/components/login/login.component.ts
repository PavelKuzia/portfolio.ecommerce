import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { NgIf } from '@angular/common';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, RouterLinkActive, ReactiveFormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.createFormControls();
  }

  createFormControls() {
    this.loginForm = new FormGroup({
      email: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSubmit() {
    if (
      this.loginForm.value['email'] === '' ||
      this.loginForm.value['password'] === ''
    ) {
      return;
    }

    this.authenticationService.login(this.loginForm.value).subscribe({
      error: (e) => (this.errorMessage = 'Wrong Credentials'),
      next: (v) => {
        console.log('Token: ', v.token);
        this.authenticationService.authenticated$.next(true);
        this.authenticationService.fullName$.next(v.fullName);
        this.storageService.setItem('token', v.token);
        this.storageService.setItem('email', this.loginForm.value['email']);
        this.storageService.setItem('username', v.fullName);
        this.router.navigateByUrl('/products');
      },
    });
  }

  get password() {
    return this.loginForm.get('password');
  }
  get email() {
    return this.loginForm.get('email');
  }
}
