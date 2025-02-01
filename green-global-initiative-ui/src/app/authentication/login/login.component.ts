import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup,FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private authenticationService: AuthService, private router: Router, private apiService: ApiService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Validators for email
      password: ['', [Validators.required, Validators.minLength(6)]], // Validators for password
    });
  }

  onSubmit() {
    this.errorMessage = '';
    if (this.loginForm.valid) {
      let requestObject = {email: this.loginForm.value.email, password: this.authenticationService.hashPassword(this.loginForm.value.password, 'atu')};
      this.apiService.post('users/login', requestObject).subscribe((response) => {
        console.log('Login Successful!', response);
        this.authenticationService.setUser(
          {id: response.userId, lastName: response.lastName, firstName: response.firstName, isAdmin: response.admin}
        );
        this.authenticationService.login();
        this.router.navigate(['/home']);
      },
      error => {
        if (error.status === 401) { // HTTP status code for Unauthorized
          this.errorMessage = 'Wrong password, please try again!';
        } else if (error.status === 400) {
          this.errorMessage = 'Invalid input, please check your credentials!';
        } else {
          this.errorMessage = 'Something went wrong, please try again later!';
        }
      });
    } else {
      console.log('Form Invalid!');
    }
  }
}
