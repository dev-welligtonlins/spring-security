import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";
import { UserState } from "../auth/state/user.state";

export const authGuestGuard: CanActivateFn = () => {

  const userState = inject(UserState);
  const router = inject(Router);

  if (userState.isAuthenticated()) {
    return router.parseUrl('/');
  }
  
  return true;
};