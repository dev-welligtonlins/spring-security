import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/authRole.guard';

export const routes: Routes = [
    {
        path: 'register-login',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
    path: 'register',
    loadComponent: () => import('./features/auth/pages/register-and-login-page/register-and-login-page').then(m => m.RegisterAndLoginPage)
    },
    {
    path: 'barbershop',
    loadComponent: () => import('./features/barbershop/pages/home-page/home-page').then(m => m.HomePage)
    }
,
    {
    path: 'services',
    loadComponent: () => import('./features/services/pages/services-page/services-page').then(m => m.ServicesPage)
    }
    // ,
    // {
    // path: 'barbershop', component: BarbershopComponent,
    // canActivate: [authGuard, roleGuard],
    // data: { roles: ['BARBERSHOP'] }
    // }
];

