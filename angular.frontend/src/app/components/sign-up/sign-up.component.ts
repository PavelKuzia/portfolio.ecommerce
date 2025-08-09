import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CustomValidator } from '../../validators/custom-validator';
import { NgIf } from '@angular/common';
import { AuthenticationService } from '../../services/authentication.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-sign-up',
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css',
})
export class SignUpComponent implements OnInit {
  signUpForm!: FormGroup;
  badCredentialsErrorMessage: string = '';

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.createFormControls();
  }
  private createFormControls() {
    this.signUpForm = new FormGroup({
      fullName: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        CustomValidator.notOnlyWhiteSpace,
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$'),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        CustomValidator.notOnlyWhiteSpace,
      ]),
    });
  }

  onLogInAfterSignUp(data: LoginData) {
    this.authenticationService.login(data).subscribe({
      next: (v) => {
        this.authenticationService.authenticated$.next(true);
        this.authenticationService.fullName$.next(v.fullName);
        this.storageService.setItem('token', v.token);
        this.storageService.setItem('email', this.email?.value);
        this.storageService.setItem('username', v.fullName);
        this.router.navigateByUrl('products');
      },
    });
  }

  onSubmit() {
    if (this.signUpForm.invalid) {
      this.signUpForm.markAllAsTouched();
      return;
    }

    this.authenticationService.signUp(this.signUpForm.value).subscribe({
      complete: () => {
        this.router.navigateByUrl('/');
        this.onLogInAfterSignUp({
          email: this.email?.value,
          password: this.password?.value,
        });
      },
      error: (error) => {
        this.badCredentialsErrorMessage = 'Bad Credentials';
      },
    });
  }

  get fullName() {
    return this.signUpForm.get('fullName');
  }
  get password() {
    return this.signUpForm.get('password');
  }
  get email() {
    return this.signUpForm.get('email');
  }
}

interface LoginData {
  email: string;
  password: string;
}
