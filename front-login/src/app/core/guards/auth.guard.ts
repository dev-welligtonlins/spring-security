import { inject, Injectable } from '@angular/core';
import { CanActivate, CanActivateFn, Router } from '@angular/router';

import { UserState } from '../auth/state/user.state';


export const authGuard: CanActivateFn = () => {

    const userState = inject(UserState);
    const router = inject(Router);

    const user = userState.user();

    if (!user) {
      router.navigate(['/login']);
      return false;
    }

    return true;
};