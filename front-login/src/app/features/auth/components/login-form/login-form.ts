import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../core/auth/service/auth.service';
import { LoginRequest } from '../../../../core/auth/model/LoginRequest';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { switchMap } from 'rxjs';
import { AuthRedirectService } from '../../../../core/auth/service/authRedirect.service';

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private authService: AuthService, 
    private authRedirectService: AuthRedirectService, 
    private fb: FormBuilder) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      login: '',
      password: ''
    })
  }


  login() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    const data: LoginRequest = this.loginForm.value;
    this.authService.login(data).pipe(
      switchMap(() => this.authService.loadUser())
    ).subscribe({
      next: (user) => {
        this.authRedirectService.redirect(user);
      },
      error: (err) => {
        console.error('Erro login', err);
      }
    });
  }
}
