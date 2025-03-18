import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { GrantsService } from '../services/grants.service';
import { AuthService } from '../../authentication/services/auth.service';

@Component({
  selector: 'app-grant-application',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './grant-application.component.html',
  styleUrl: './grant-application.component.scss'
})
export class GrantApplicationComponent implements OnInit{

  activeUser !: { firstName: string, id: number, isAdmin: boolean, lastName: string, email: string };
  grantForm: FormGroup;
  grantOptions:any[] = [];

  constructor(private fb: FormBuilder, private grantsService: GrantsService, private authService: AuthService) {
    this.grantForm = this.fb.group({
      applicantName: [{value:'', disabled: true}, [Validators.required, Validators.minLength(3)]],
      email: [{value:'', disabled: true}, [Validators.required, Validators.email]],
      organizationName: ['', Validators.required],
      grantName: ['', [Validators.required]],
      budget: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      projectDescription: ['', [Validators.required, Validators.minLength(20)]]
    });
    this.grantsService.getGrants('').subscribe(x=>
      {
        this.grantOptions = x
      }
      ,(error)=> {
        console.log('Failed to get the grants');
    });
  }

  ngOnInit(){
    const userData: string|null = this.authService.getUser();
    this.activeUser = JSON.parse(userData?userData: JSON.stringify ({ firstName: '', id: null , isAdmin: false, lastName: '', email: '' }));
    this.grantForm.controls['applicantName'].setValue(this.activeUser?.firstName + ' ' + this.activeUser?.lastName)
    this.grantForm.controls['email'].setValue(this.activeUser.email);
  }

  onSubmit() {
    if (this.grantForm.valid) {
      console.log('Grant Application Submitted:', this.grantForm.value);
      let requestBody = this.getRequestBody(this.grantForm.value);
      this.grantsService.postGrantApplications('',requestBody).subscribe((res)=>{
        console.log(res);
      }, (err)=>{
        console.log(err);
      });
      this.grantForm.reset();
    } else {
      alert(`Please fill the required fields correctly`);
    }
  }
  

  getRequestBody(grantApplicationForm: any): any{
    return {
      applicationId: 0,
      userId: this.activeUser.id,
      organizationName: grantApplicationForm.organizationName,
      grantId: Number(grantApplicationForm.grantName),
      applicationStatus: 'In Progress',
      approvalDate: null,
      requestedAmount: grantApplicationForm.budget,
      projectDescription: grantApplicationForm.projectDescription
    };
  }
}