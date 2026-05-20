import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ServicesService } from '../../services/services.service';
import { CreateServiceDTO } from '../../models/create-service.model';
import { ServicesState } from '../../state/services.state';

@Component({
  selector: 'app-create-service',
  imports: [ReactiveFormsModule],
  templateUrl: './create-service.html',
  styleUrl: './create-service.scss',
})
export class CreateService implements OnInit {
  serviceForm!: FormGroup;
  private readonly servicesState = inject(ServicesState);
  
  constructor(
    private servicesService: ServicesService, 
    private fb: FormBuilder) {}

  ngOnInit() {
    this.serviceForm = this.fb.group({
      serviceDescription: '',
      value: 0.0,
      category: ''
    })
  }


  createService() {
    if (this.serviceForm.invalid) {
      this.serviceForm.markAllAsTouched();
      return;
    }

    const data: CreateServiceDTO = this.serviceForm.value;
    this.servicesService.create(data).pipe(

    ).subscribe({
      next: service => {
        this.servicesState.addService(service);
      },
      error: (err) => {
        console.error('Erro ao criar serviço!', err);
      }
    });
  }
}

