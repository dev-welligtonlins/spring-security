import { computed, Injectable, signal } from "@angular/core";
import { Service } from "../models/service.model";
import { ServiceEnumModal } from "../models/service-enum.model";

@Injectable({
    providedIn: 'root'
})
export class ServicesState {
    
    private readonly _services = signal<Service[]>([]);
    readonly services = this._services.asReadonly();

    private readonly _selectedService = signal<Service | null>(null);
    readonly selectedService = this._selectedService.asReadonly();

    private readonly _modalType = signal<ServiceEnumModal | null>(null);
    readonly modalType = this._modalType.asReadonly();

    readonly isModalOpen = computed(() => this.modalType() !== null);
    
    setServices( services: Service[] ): void {
        this._services.set(services);
    }

    addService(service: Service): void {
       this._services.update(list => [service, ...list]);
    }

    updateService(update: Service): void {
       this._services.update(list => list.map(service => service.id === update.id ? update : service));
    }

    removeService(id: string): void {
       this._services.update(list => list.filter(service => service.id !== id));
    }

    openCreateModal() { 
        this._selectedService.set(null);
        this._modalType.set(ServiceEnumModal.CREATE);
    }

    openUpdateModal(service: Service) { 
        this._selectedService.set(service);
        this._modalType.set(ServiceEnumModal.UPDATE);
    }

    closeModal() { 
        this._selectedService.set(null);
        this._modalType.set(null);
    }
}