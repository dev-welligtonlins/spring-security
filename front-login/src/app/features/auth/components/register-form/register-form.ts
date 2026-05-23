import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/auth/services/auth.service';
import { RegisterRequest } from '../../../../core/auth/models/RegisterRequest';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm {

  private authService = inject(AuthService);
  private fb = inject(FormBuilder);

  registerForm = this.fb.nonNullable.group({
      login: ['', Validators.required],
      name: ['', Validators.required],
      phone: ['', Validators.required],
      password: ['', Validators.required]
    });

  registerBarbershop() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const data: (RegisterRequest) = this.registerForm.getRawValue();

    this.authService
      .registerBarbershop(data)
      .subscribe({

        error: err => {

          console.error(
            'Erro register',
            err
          );
        }
      });
  }
}
