import { Injectable, signal, computed } from '@angular/core';

import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserState {

    user = signal<User | null>(null);

    isAuthenticated = computed(() => !!this.user());

    isClient = computed(() =>
      this.user()?.role === 'CLIENT'
    );

    isBarbershop = computed(() =>
      this.user()?.role === 'BARBERSHOP'
    );

    setUser(user: User | null) {
      this.user.set(user);
    }

    clear() {
      this.user.set(null);
    }


    getUser(): User | null {
        return this.user();
    }

}