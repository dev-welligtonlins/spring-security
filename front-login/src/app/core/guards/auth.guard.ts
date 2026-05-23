import { inject, Injectable } from '@angular/core';
import { CanActivate, CanActivateFn, Router } from '@angular/router';
import { UserState } from '../auth/state/user.state';



export const authGuard: CanActivateFn = () => {

  const userState = inject(UserState);
  const router = inject(Router);

  if (userState.isAuthenticated()) {
    return true;
  }

  return router.parseUrl('/auth/login');
};