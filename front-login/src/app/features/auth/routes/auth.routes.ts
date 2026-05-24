import { Routes }
from '@angular/router';

export const AUTH_ROUTES:
Routes = [

  {
    path: '',

    loadComponent: () =>
      import('../pages/register-and-login-page/register-and-login-page'
      ).then(m => m.RegisterAndLoginPage)
  }
  // {
  //   path: '',

  //   loadComponent: () =>
  //     import('../pages/register-and-login-page/register-and-login-page'
  //     ).then(m => m.RegisterAndLoginPage)
  // },
];