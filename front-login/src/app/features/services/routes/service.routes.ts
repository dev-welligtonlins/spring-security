import { Routes }
from '@angular/router';

export const SERVICES_ROUTES:
Routes = [

  {
    path: '',
    loadComponent: () => import('../pages/services-page/services-page')
      .then(m => m.ServicesPage)
  }
];