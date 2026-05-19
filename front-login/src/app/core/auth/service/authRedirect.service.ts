import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthRedirectService {

  constructor(private router: Router) {}

  redirect(user: User): void {

    switch (user.role) {

      case 'CLIENT':
        this.router.navigate(['/client']);
        break;

      case 'BARBERSHOP':
        this.router.navigate(['/barbershop']);
        break;

      default:
        this.router.navigate(['/']);
    }
  }
}