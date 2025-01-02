import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { SecurityComponent } from './components/security/security.component';
import { PatientComponent } from './components/patient/patient.component';
import { SetupComponent } from './components/setup/setup.component';
import { OtCompletedListComponent } from './components/ot-completed-list/ot-completed-list.component';
import { AuthGuard } from './auth-guard/auth.guard';

export const routes: Routes = [

  // defualt route
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },

  // ----------------after login ------
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'patient',
        component: PatientComponent,canActivate: [AuthGuard]
      },
      {
        path: 'OT_Completed_List',
        component: OtCompletedListComponent,canActivate: [AuthGuard]
      },
      {
        path: 'security',
        component: SecurityComponent,canActivate: [AuthGuard]
      },
      {
        path: 'setup',
        component: SetupComponent,canActivate: [AuthGuard]
      },

    ]
  },

];
