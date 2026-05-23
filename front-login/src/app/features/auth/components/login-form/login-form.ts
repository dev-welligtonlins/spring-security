import { Component, inject } from "@angular/core";
import { AuthService } from "../../../../core/auth/services/auth.service";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { LoginRequest } from "../../../../core/auth/models/LoginRequest";

@Component({ 
  selector: 'app-login-form', 
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login-form.html', 
  styleUrl: './login-form.scss',
 })
export class LoginForm {

  private authService = inject(AuthService);

  private fb = inject(FormBuilder);

  loginForm = this.fb.nonNullable.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });

  login() {

    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    const data: (LoginRequest) = this.loginForm.getRawValue();

    this.authService
      .login(data)
      .subscribe({

        error: err => {

          console.error(
            'Erro login',
            err
          );
        }
      });
  }
}