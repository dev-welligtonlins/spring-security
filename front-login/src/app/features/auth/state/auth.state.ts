// import { Injectable, signal } from "@angular/core";
// import { AuthStateModel } from "../models/auth-state.model";

// @Injectable({
//     providedIn: 'root'
// })
// export class AuthState {
    
//     private readonly _authState = signal<AuthStateModel>({
//       user: null,
//       loading: false,
//       authenticated: false,
//       error: null
//     });

//     readonly services = this._services.asReadonly();

//     setServices( services: Service[] ): void {
//         this._services.set(services);
//     }

//     addService(service: Service): void {
//        this._services.update(list => [service, ...list]);
//     }

//     updateService(update: Service): void {
//        this._services.update(list => list.map(service => service.id === update.id ? update : service));
//     }

//     removeService(id: string): void {
//        this._services.update(list => list.filter(service => service.id !== id));
//     }
// }