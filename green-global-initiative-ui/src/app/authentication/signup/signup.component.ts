import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  signupForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private authenticationService: AuthService, private apiService: ApiService) {
      this.signupForm = this.fb.group({
        firstName: ['', [Validators.required, Validators.minLength(3)]], // Validators for firstName
        lastName: ['', [Validators.required, Validators.minLength(1)]], // Validators for lastName
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]], // Validators for password
        confirmPassword: ['', [Validators.required, Validators.minLength(6)]], // Validators for confirmPassword
      });
    }
  
    onSubmit() {
      if (this.signupForm.valid) {
        let apiRequestBody = this.mapToSignUpRequest(this.signupForm.value);
        this.apiService.post('users/signup', apiRequestBody).subscribe((response) => {
          if(response.error) {
            console.log('Signup Failed!', response.error);
          }
          else {
            console.log('Signup Successful!', response);
            this.router.navigate(['/login']);
          }    
        });
      } else {
        console.log('Form Invalid!');
      }
    }

    mapToSignUpRequest(signupForm: any): any {
      return {
        first_name: signupForm.firstName,
        last_name: signupForm.lastName,
        email: signupForm.email,
        password: this.authenticationService.hashPassword(signupForm.password, 'atu'),
        isAdmin: false,
        last_login: new Date(),
      };
    }
}
