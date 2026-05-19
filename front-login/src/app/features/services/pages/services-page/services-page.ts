import { Component, inject, OnInit, signal } from '@angular/core';
import { ServicesService } from '../../services/services.service';
import { Service } from '../../models/service.model';

@Component({
  selector: 'app-services-page',
  imports: [],
  templateUrl: './services-page.html',
  styleUrl: './services-page.scss',
})
export class ServicesPage implements OnInit {

  private servicesService = inject(ServicesService);

  services = signal<Service[]>([]);

  ngOnInit(): void {

    this.loadServices();
  }

  loadServices(): void {

    this.servicesService.meServices().subscribe({
      next: (services) => {
        this.services.set(services);
      },
      error: (err) => {
        console.error('Erro ao carregar services!', err);
      }
    });
  }

}
