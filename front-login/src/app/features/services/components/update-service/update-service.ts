import { Component, effect, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ServicesState } from '../../state/services.state';
import { ServicesService } from '../../services/services.service';
import { Service } from '../../models/service.model';

@Component({
  selector: 'app-update-service',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './update-service.html',
  styleUrl: './update-service.scss',
})
export class UpdateService {

  protected readonly servicesState = inject(ServicesState);
  private readonly servicesService = inject(ServicesService);
  private readonly fb = inject(FormBuilder);

  serviceForm = this.fb.nonNullable.group({
    serviceDescription: [''],
    duration: [0.0],
    value: [0.0],
    serviceCategory: ['']
  });

  constructor() { 
    effect(() => {

      const service = this.servicesState.selectedService();

      if (!service) { return; }

      this.serviceForm.patchValue({
        serviceDescription: service.serviceDescription,
        duration: service.duration,
        value: service.value,
        serviceCategory: service.serviceCategory
      });
    });

  }

  updateService() {
    if (this.serviceForm.invalid) {
      this.serviceForm.markAllAsTouched();
      return;
    }

    const service = this.servicesState.selectedService();
    if (!service) { return; }
    const data: Service = {
      ...service, ...this.serviceForm.getRawValue()
    };
    this.servicesService.update(service.id, data)
      .subscribe({
        next: updated => {
          this.servicesState.updateService(updated);
          this.servicesState.closeModal();
        },

        error: err => {
          console.error(
            'Erro ao atualizar serviço', err
          );
        }
      });
  }
}
