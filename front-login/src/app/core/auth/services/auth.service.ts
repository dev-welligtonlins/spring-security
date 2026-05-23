import {Injectable, inject} from '@angular/core';
import {switchMap, tap} from 'rxjs';

import { AuthApi } from '../api/auth.api';

import { AuthRedirectService } from './authRedirect.service';

import { LoginRequest } from '../models/LoginRequest';

import { UserState } from '../state/user.state';
import { RegisterRequest } from '../models/RegisterRequest';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private authApi = inject(AuthApi);

    private authRedirectService = inject(AuthRedirectService);

    private userState = inject(UserState);

    login(data: LoginRequest) {
        return this.authApi.login(data)
            .pipe(
                switchMap(() =>
                    this.authApi.me()
                ),
                tap(user => {
                    this.userState.setUser(user);
                    this.authRedirectService.redirect(user);
                })
            );
    }

    registerBarbershop(data: RegisterRequest) {
        return this.authApi.registerBarbershop(data)
            .pipe(
                switchMap(() =>
                    this.authApi.me()
                ),
                tap(user => {
                    this.userState.setUser(user);
                    this.authRedirectService.redirect(user);
                })
            );
    }

    loadUser() {
        return this.authApi.me()
            .pipe(
                tap(user => {
                    this.userState.setUser(user);
                })
            );
    }

    logout() {

        this.userState.clear();
    }
}