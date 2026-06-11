import { inject, Injectable } from "@angular/core";
import { ServicesApi } from "../api/services.api";
import { CreateServiceDTO } from "../models/create-service.model";
import { UpdateServiceDTO } from "../models/update-service.model";

@Injectable({
    providedIn: 'root'
})
export class ServicesService {

    private readonly serviceApi = inject(ServicesApi);

    loadServices() {
        return this.serviceApi.meServices();
    }

    create(data: CreateServiceDTO) {
        return this.serviceApi.create(data);
    }

    update(service_id: string, data: UpdateServiceDTO) {
        return this.serviceApi.update(service_id, data);
    }

    remove(service_id: string) {
        return this.serviceApi.remove(service_id);
    }
}