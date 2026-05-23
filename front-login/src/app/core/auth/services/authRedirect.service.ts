import { Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthRedirectService {

  private router = inject(Router);

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