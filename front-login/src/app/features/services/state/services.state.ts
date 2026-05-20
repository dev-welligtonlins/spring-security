import { Injectable, signal } from "@angular/core";
import { Service } from "../models/service.model";

@Injectable({
    providedIn: 'root'
})
export class ServicesState {
    
    private readonly _services = signal<Service[]>([]);

    readonly services = this._services.asReadonly();

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
}