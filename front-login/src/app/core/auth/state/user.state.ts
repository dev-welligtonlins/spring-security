import { Injectable, signal, computed } from '@angular/core';

import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
@Injectable({
  providedIn: 'root'
})
export class UserState {

  private readonly _user = signal<User | null>(null);

  readonly user = this._user.asReadonly();

  readonly isAuthenticated = computed(() => !!this._user());

  readonly isClient = computed(() => this._user()?.role === 'CLIENT');

  readonly isBarbershop = computed(() => this._user()?.role === 'BARBERSHOP');

  setUser(user: User) {
    this._user.set(user);
  }

  clear() {
    this._user.set(null);
  }
}