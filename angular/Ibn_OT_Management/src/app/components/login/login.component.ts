import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Auth } from '../../models/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {


  username: string = '';
  password: string = '';
  message: string = '';
  response!:boolean;

  
  passwordFieldType: string = 'password'; // Default to password field
  isPasswordVisible: boolean = false;

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService:AuthService,
) {}

  onLogin() {
    this.authService.login(new Auth(this.username,this.password))
    .subscribe((response) => {
        this.response = response.success;
        this.message = response.message;
        if (this.message == "login successful") {
          this.router.navigate(['/setup']);
        }else{
          alert(this.message+" ! username or password invalid!!")
        }
      },
      error => {
        alert("Login Unsuccess!")
      }
    );
   
  }




  
}
