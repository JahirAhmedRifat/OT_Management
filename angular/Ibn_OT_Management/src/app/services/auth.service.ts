import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap, catchError, of } from 'rxjs';
import { Auth } from '../models/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //dev server
  // private apiUrl = "http://192.168.0.73:8090/ot_management/api/auth";

  //ATI local server
  // private apiUrl = "http://192.168.0.169:8080/ot_management/api/auth";

  //live server
  private apiUrl = "http://202.51.176.30:8080/ot_management/api/auth";


  private tokenKey = 'authToken';

  constructor(private http: HttpClient, private router: Router) { }

  login(auth: Auth): Observable<Auth> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Auth>(this.apiUrl + "/login", auth, { headers, withCredentials: true }).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem(this.tokenKey, response.token);
        }
      }),
      catchError(this.handleError<any>('login', null))
    );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  // ------------- Change admin password -------------------
  changePassword(PasswordChangeRequest: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + "/change-password", PasswordChangeRequest);
  }

}
