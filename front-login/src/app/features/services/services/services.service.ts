import { inject, Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Service } from "../models/service.model";
import { CreateServiceDTO } from "../models/create-service.model";



@Injectable({
  providedIn: 'root'
})
export class ServicesService {
    
    private API = 'http://localhost:8080/services';
 
    private http = inject(HttpClient);

    meServices(): Observable<Service[]> {
        return this.http.get<Service[]>(
            `${this.API}/me`, 
            {withCredentials: true}
        );
    }

    create(data: CreateServiceDTO): Observable<Service> {
        return this.http.post<Service>(
            `${this.API}/create`, data, 
            {withCredentials: true}
        );
    }

    update(service_id: string, data: Service): Observable<Service> {
        return this.http.put<Service>(
            `${this.API}/update/${service_id}`, data, 
            {withCredentials: true}
        );
    }

    remove(id: string): Observable<void>{
        return this.http.delete<void>(
            `${this.API}/remove/${id}`,
            {withCredentials: true}
        );
    }
    // logout() {
    // }

}