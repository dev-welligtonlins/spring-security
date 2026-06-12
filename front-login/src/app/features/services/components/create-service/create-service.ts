import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ServicesService } from '../../services/services.service';
import { CreateServiceDTO } from '../../models/create-service.model';
import { ServicesState } from '../../state/services.state';

@Component({
  selector: 'app-create-service',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './create-service.html',
  styleUrl: './create-service.scss',
})
export class CreateService {
  protected readonly servicesState = inject(ServicesState);
  private readonly servicesService = inject(ServicesService);
  private readonly fb = inject(FormBuilder);

  serviceForm = this.fb.nonNullable.group({
    serviceDescription: [''],
    value: [0.0],
    category: ['']
  });

  createService() {
    if (this.serviceForm.invalid) {
      this.serviceForm.markAllAsTouched();
      return;
    }

    const data: CreateServiceDTO = this.serviceForm.getRawValue();
    this.servicesService.create(data).pipe(

    ).subscribe({
      next: service => {
        this.servicesState.addService(service);
        this.servicesState.closeModal();

      },
      error: (err) => {
        console.error('Erro ao criar serviço!', err);
      }
    });
  }
}

