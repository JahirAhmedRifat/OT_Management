import { HttpInterceptorFn } from '@angular/common/http';
import { HttpRequest, HttpHandlerFn, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const tokenInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const token = localStorage.getItem('authToken');
  const clonedReq = token ? req.clone({
    headers: req.headers.set('Authorization', 'Bearer ' + token)
  }) : req;

  return next(clonedReq).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        // Token expired, log out
        localStorage.removeItem('authToken');
        router.navigate(['/login'], { replaceUrl: true }).then(() => {
          window.location.reload();
        });
      }
      return throwError(() => error);
    })
  );
};
