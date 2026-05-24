import { Component, inject } from '@angular/core';
import { UserState } from '../../../core/auth/state/user.state';
import { AuthService } from '../../../core/auth/services/auth.service';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {

  protected userState = inject(UserState);

  private authService = inject(AuthService);

  searchQuery = '';

  user = this.userState.user;

  logout() {

    this.authService.logout();
  }
}
