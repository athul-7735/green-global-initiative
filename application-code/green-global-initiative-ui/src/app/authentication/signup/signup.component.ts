import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ApiService } from '../services/api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  signupForm: FormGroup;
  isPasswordVisible = false;

  constructor(private fb: FormBuilder, private router: Router, private authenticationService: AuthService, 
    private apiService: ApiService, private toastr: ToastrService) {
      this.signupForm = this.fb.group({
        firstName: ['', [Validators.required, Validators.minLength(3)]], // Validators for firstName
        lastName: ['', [Validators.required, Validators.minLength(1)]], // Validators for lastName
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]], // Validators for password
        confirmPassword: ['', [Validators.required, Validators.minLength(6)]], // Validators for confirmPassword
      }, { validators: this.passwordMatchValidator }
    );
  }
   
  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');
    if (password?.value !== confirmPassword?.value) {
      confirmPassword?.setErrors({ passwordMismatch: true });
    } else {
      confirmPassword?.setErrors(null);
    }
  }

  togglePasswordVisibility(){
    this.isPasswordVisible = !this.isPasswordVisible;
  }
  
  onSubmit() {
    if (this.signupForm.valid) {
      this.authenticationService.removeItem();
      let apiRequestBody = this.mapToSignUpRequest(this.signupForm.value);
      this.apiService.post('users/signup', apiRequestBody).subscribe((response) => {
        if(response.error) {
          console.log('Signup Failed!', response.error);
          this.toastr.error('User Registration Failed', 'Error',  {
            progressBar: true, closeButton: true 
          });
        }
        else {
          console.log('Signup Successful!', response);
          this.toastr.success('User Successfully Registered!', 'Success',  {
            progressBar: true, closeButton: true 
          });
          this.router.navigate(['/login']);
        }    
      }, (err)=>
        this.toastr.error('User Registration Failed', 'Error',  {
          progressBar: true, closeButton: true 
        })
      );
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
