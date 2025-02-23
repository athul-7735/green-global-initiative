import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup,FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../services/api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private authenticationService: AuthService, private router: Router, 
    private apiService: ApiService, private toastr: ToastrService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Validators for email
      password: ['', [Validators.required, Validators.minLength(6)]], // Validators for password
    });
  }

  onSubmit() {
    this.errorMessage = '';
    if (this.loginForm.valid) {
      this.authenticationService.removeItem();
      let requestObject = {email: this.loginForm.value.email, password: this.authenticationService.hashPassword(this.loginForm.value.password, 'atu')};
      this.apiService.post('users/login', requestObject).subscribe((response) => {
        console.log('Login Successful!', response);
        this.authenticationService.setItem(response.token);
        let userObject = this.authenticationService.getDecodedAccessToken(response.token);
        console.log(userObject);
        this.authenticationService.setUser(
          {id: userObject.id, lastName: userObject.lastName, email:userObject.email, firstName: userObject.firstName, isAdmin: userObject.admin}
        );
        this.authenticationService.login();
        this.router.navigate(['/home']);
        this.toastr.success('Login Successfull', 'Success',  {
          progressBar: true, closeButton: true 
        });
      },
      error => {
        if (error.status === 401) { // HTTP status code for Unauthorized
          this.errorMessage = 'Wrong password/email, please try again!';
        } else if (error.status === 400) {
          this.errorMessage = 'Invalid input, please check your credentials!';
        } else {
          this.errorMessage = 'Something went wrong, please try again later!';
        }
      });
    } else {
      this.toastr.error('Invalid Form!', 'Error',  {
        progressBar: true, closeButton: true 
      });
      console.log('Form Invalid!');
    }
  }
}
