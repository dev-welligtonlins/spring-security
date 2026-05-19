import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { LoginForm } from '../../components/login-form/login-form';
import { RegisterForm } from '../../components/register-form/register-form';

@Component({
  selector: 'app-register-and-login-page',
  standalone: true,
  imports: [CommonModule, LoginForm, RegisterForm],
  templateUrl: './register-and-login-page.html',
  styleUrl: './register-and-login-page.scss',
})
export class RegisterAndLoginPage {
  // para selecionar o component de login e register
  isLogin = true;

  toggleMode() {
    this.isLogin = !this.isLogin;
  }

}
