import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { UserState } from '../../../core/auth/state/user.state';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss',
})
export class SideBar {

  protected readonly userState = inject(UserState);
  protected readonly user = this.userState.user;

}
