import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../../../../environments/environment.dev";
import { Service } from "../models/service.model";
import { CreateServiceDTO } from "../models/create-service.model";
import { UpdateServiceDTO } from "../models/update-service.model";

@Injectable({
    providedIn: 'root'
})
export class ServicesApi {

    private http = inject(HttpClient);

    private baseUrl = `${environment.apiUrl}services`;

    meServices() {
        return this.http.get<Service[]>(
            `${this.baseUrl}/me`,
            { withCredentials: true }
        );
    }

    create(data: CreateServiceDTO) {
        return this.http.post<Service>(
            `${this.baseUrl}/create`,
            data
        );
    }

    update(service_id: string, data: UpdateServiceDTO) {
        return this.http.put<Service>(
            `${this.baseUrl}/update/${service_id}`, data, 
            {withCredentials: true}
        );
    }

    remove(service_id: string) {
        return this.http.delete<void>(
            `${this.baseUrl}/remove/${service_id}`, 
            {withCredentials: true}
        );
    }    
}