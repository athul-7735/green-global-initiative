import { Component, OnInit } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import { ContactusService } from './services/contactus.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../authentication/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contact-us',
  imports: [MatIconModule, FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './contactus.component.html',
  styleUrl: './contactus.component.scss'
})
export class ContactUsComponent implements OnInit {

  contactUsForm: FormGroup;

  constructor(private contact: ContactusService, private fb: FormBuilder, private authenticationService: AuthService, private toastr: ToastrService) { 
    this.contactUsForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required]], 
      phone: ['', [Validators.required]],
      message: ['', [Validators.required]]
    })
  }

  ngOnInit() {
  }

  onSubmit() {
    this.authenticationService.removeItem();
    let requestObject = this.contactUsForm.value;
    this.contact.postQuery('contact-us', requestObject).subscribe((response) => {
      console.log('Query Submitted!', response);
      this.toastr.success('Query submitted successfully', 'Success',  {
        progressBar: true, closeButton: true , timeOut: 500000
      });
    },(err)=>{
      console.log(err);
      this.toastr.error('Invalid Query!', 'Error',  {
        progressBar: true, closeButton: true 
      });
    });
    this.contactUsForm.reset();
  }
}
