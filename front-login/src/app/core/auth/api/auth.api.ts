import { inject, Injectable } from "@angular/core";
import { User } from "../models/user.model";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { LoginRequest } from "../models/LoginRequest";
import { UserState } from "../state/user.state";
import { environment } from "../../../../environments/environment.dev";
import { RegisterRequest } from "../models/RegisterRequest";


@Injectable({
  providedIn: 'root'
})
export class AuthApi {

  private http = inject(HttpClient);

  private baseUrl = `${environment.apiUrl}auth`;

  login(data: LoginRequest) {

    return this.http.post<void>(
      `${this.baseUrl}/login`,
      data
    );
  }

  registerBarbershop(data: RegisterRequest) {

    return this.http.post<void>(
      `${this.baseUrl}/register/barbershop`,
      data
    );
  }

  me() {

    return this.http.get<User>(
      `${this.baseUrl}/me`

    );
  }
}