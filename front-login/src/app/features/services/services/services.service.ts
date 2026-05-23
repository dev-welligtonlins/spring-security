import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Service } from "../models/service.model";
import { CreateServiceDTO } from "../models/create-service.model";



@Injectable({
  providedIn: 'root'
})
export class ServicesService {
    
    private API = 'http://localhost:8080/services';
 
    constructor(private http: HttpClient){}

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

    // logout() {
    // }

}