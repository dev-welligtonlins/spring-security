import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/authRole.guard';
import { Main } from './shared/layout/main/main';

export const routes: Routes = [
    {
        path: 'auth',
        loadChildren: () => import('./features/auth/routes/auth.routes')
            .then(m => m.AUTH_ROUTES)
    },
    {
        path: '',
        component: Main,
        children: [
            {
                path: 'barbershop',

                loadChildren: () =>
                    import('./features/barbershop/routes/barbershop.routes')
                        .then(m => m.BARBERSHOP_ROUTES)
            },

            {
                path: 'services',

                loadChildren: () =>
                    import('./features/services/routes/service.routes')
                        .then(m => m.SERVICES_ROUTES)
            }]
    }
];

