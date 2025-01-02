import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-security',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './security.component.html',
  styleUrl: './security.component.css'
})
export class SecurityComponent {

  message:any;

  PasswordChangeRequest:any={
    username:'',
    newPassword:''
  };

  passwordFieldType: string = 'password'; // Default to password field

  isPasswordVisible: boolean = false;

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  constructor(private authService: AuthService, private router: Router){
    
  }

  changePassword(){
    this.authService.changePassword(this.PasswordChangeRequest)
    .subscribe((res: any) => {
      this.message = res.message;
      alert(this.message);
    });
  }


}
