import { Component, inject } from '@angular/core';
import { UserState } from '../../../core/auth/state/user.state';
import { AuthService } from '../../../core/auth/services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.scss'],
})
export class Navbar {

  perfil = {
    nome: 'Faca & Bigode',
    user: 'Nome do Usuário',
    fotoUrl: 'caminho/para/sua/imagem.jpg' // URL real da imagem
  };

  protected userState = inject(UserState);

  private authService = inject(AuthService);

  searchQuery = '';

  // exemplo de dados do dashboard que serão filtrados
  items = [
    { id: 1, title: 'Corte Masculino', desc: 'Corte tradicional' },
    { id: 2, title: 'Barba', desc: 'Aparar e modelar' },
    { id: 3, title: 'Coloração', desc: 'Tintura e retoque' },
    { id: 4, title: 'Sobrancelha', desc: 'Design' }
  ];

  get filteredItems() {
    const q = this.searchQuery.trim().toLowerCase();
    if (!q) return this.items;
    return this.items.filter(i =>
      (i.title + ' ' + i.desc).toLowerCase().includes(q)
    );
  }

  trackById(index: number, item: any) {
    return item.id;
  }

  // 1. O construtor é usado apenas para INJEÇÃO de dependências
  constructor(private router: Router) {
    // Nada mais deve ser definido aqui
  }

  user = this.userState.user;

  logout() {
    this.authService.logout();
    // 2. Limpar a Sessão
    localStorage.removeItem('auth_token');
    // 3. Redirecionar para a tela de login
    this.router.navigate(['/login']);
  }
}
