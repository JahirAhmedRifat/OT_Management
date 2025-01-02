import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { SecurityComponent } from './components/security/security.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import {  faStethoscope, faShareAlt, faPrint } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
     RouterOutlet,
     FontAwesomeModule
     ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Ibn_OT_Management';
  constructor(library: FaIconLibrary) {
    library.addIcons(faStethoscope, faShareAlt, faPrint); // necessary icon loading--
  }
}
