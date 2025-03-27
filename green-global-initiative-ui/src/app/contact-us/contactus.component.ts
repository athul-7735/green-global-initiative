import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ContactusService } from './services/contactus.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../authentication/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contact-us',
  standalone: true, // ✅ Add this to enable standalone mode
  imports: [MatIconModule, FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './contactus.component.html',
  styleUrl: './contactus.component.scss'
})
export class ContactUsComponent implements OnInit {

  contactUsForm: FormGroup;

  constructor(
    private contact: ContactusService, 
    private fb: FormBuilder, 
    private authenticationService: AuthService, 
    private toastr: ToastrService
  ) { 
    this.contactUsForm = this.fb.group({
      name: ['', [Validators.required]],  // ✅ No issue with Validators after the fix
      email: ['', [Validators.required, Validators.email]], // ✅ Added email validation
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]], // ✅ Ensures 10-digit phone number
      message: ['', [Validators.required, Validators.minLength(10)]] // ✅ Ensures message has at least 10 characters
    });
  }

  ngOnInit() { }

  onSubmit() {
    if (this.contactUsForm.invalid) {
      this.toastr.error('Please fill out all required fields correctly.', 'Error', {
        progressBar: true, closeButton: true
      });
      return;
    }

    this.authenticationService.removeItem();
    let requestObject = this.contactUsForm.value;

    this.contact.postQuery('contact-us', requestObject).subscribe(
      (response) => {
        console.log('Query Submitted!', response);
        this.toastr.success('Query submitted successfully', 'Success', {
          progressBar: true, closeButton: true, timeOut: 5000
        });
        this.contactUsForm.reset();
      },
      (err) => {
        console.log(err);
        this.toastr.error('Invalid Query!', 'Error', {
          progressBar: true, closeButton: true
        });
      }
    );
  }
}
