import { Component, inject } from '@angular/core';
import { UserState } from '../../../../core/auth/state/user.state';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
})
export class HomePage {

  userState = inject(UserState);

}
