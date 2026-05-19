import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router } from '@angular/router';

import { UserState } from '../auth/state/user.state';


export const roleGuard: CanActivateFn = ( route: ActivatedRouteSnapshot ) => {

  const userState = inject(UserState);
  const router = inject(Router);

  const user = userState.user();

  if (!user) {
    router.navigate(['/login']);
    return false;
  }

  const allowedRoles = route.data['roles'];

  if (!allowedRoles.includes(user.role)) {

    router.navigate(['/unauthorized']);

    return false;
  }

  return true;
};