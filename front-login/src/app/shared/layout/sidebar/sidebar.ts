import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { UserState } from '../../../core/auth/state/user.state';
import { SidebarState } from '../../../core/state/sidebar.state';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
})
export class SideBar {

  protected readonly sidebarState = inject(SidebarState);
  protected readonly userState = inject(UserState);
  protected readonly user = this.userState.user;

  perfil = {
    nome: 'Faca & Bigode',
    fotoUrl: 'caminho/para/sua/imagem.jpg' // URL real da imagem
  };

  close(): void {
    this.sidebarState.close();
  }
}
