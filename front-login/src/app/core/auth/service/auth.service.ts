import { Injectable } from "@angular/core";
import { User } from "../model/user.model";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { LoginRequest } from "../model/LoginRequest";
import { UserState } from "../state/user.state";



@Injectable({
  providedIn: 'root'
})
export class AuthService {
    
    private API = 'http://localhost:8080/auth';
 
    constructor(private userState: UserState, private http: HttpClient){}

    login(data: LoginRequest): Observable<any> {
        return this.http.post(
            `${this.API}/login`, 
            data,
            {withCredentials: true}
        );
    }

    loadUser(): Observable<User> {
        return this.http.get<User>(
            `${this.API}/me`,
            {withCredentials: true}).pipe(tap(user => this.userState.setUser(user))
        );
    }

    // logout() {
    // }

}