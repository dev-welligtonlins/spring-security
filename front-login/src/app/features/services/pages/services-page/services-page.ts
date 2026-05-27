import { Component, inject, OnInit, signal } from '@angular/core';
import { ServicesService } from '../../services/services.service';
import { Service } from '../../models/service.model';
import { CommonModule } from '@angular/common';
import { CreateService } from '../../components/create-service/create-service';
import { ServicesState } from '../../state/services.state';

@Component({
  selector: 'app-services-page',
  standalone: true,
  imports: [CommonModule, CreateService],
  templateUrl: './services-page.html',
  styleUrl: './services-page.scss',
})
export class ServicesPage implements OnInit {

  private readonly servicesService = inject(ServicesService);

  private readonly servicesState = inject(ServicesState);

  readonly services = this.servicesState.services;

  ngOnInit(): void {

    this.loadServices();
  }

  loadServices(): void {

    this.servicesService.meServices().subscribe({
      next: (services) => {
        this.servicesState.setServices(services);
      },
      error: (err) => {
        console.error('Erro ao carregar services!', err);
      }
    });
  }

  remove(id: string) {

    this.servicesService.remove(id).subscribe({
        next: () => {
          this.servicesState.removeService(id);
        },

        error: err => {
          console.error('Erro ao remover', err);
        }
      });
  }

}





