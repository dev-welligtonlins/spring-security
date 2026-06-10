import { Component, inject, OnInit } from '@angular/core';
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
export class UpdateService implements OnInit {
  serviceForm!: FormGroup;
  protected readonly servicesState = inject(ServicesState);

  constructor(
    private servicesService: ServicesService,
    private fb: FormBuilder) { }

  ngOnInit() {
    // form iniciando
    this.serviceForm = this.fb.group({
      serviceDescription: '',
      value: 0.0,
      category: ''
    });

    const service = this.servicesState.selectedService();
    // precisamos fazer uma verificação de verdade aqui!
    if (!service) {
      return;
    }

    // o form carrega os dados do service
    this.serviceForm.patchValue({
      serviceDescription: service.serviceDescription,
      value: service.value,
      category: service.category
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
