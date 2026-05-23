import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router } from '@angular/router';
import { UserState } from '../auth/state/user.state';



export const roleGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {

  const userState = inject(UserState);
  const router = inject(Router);

  const allowedRoles = route.data['roles'];
  const user = userState.user();

  if (user && allowedRoles.includes(user.role)) {
    return true;
  }

  return router.parseUrl('/');
};