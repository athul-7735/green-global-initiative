import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { GrantsService } from '../services/grants.service';
import { AuthService } from '../../authentication/services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { PopupWindowComponent } from '../../shared/popup-window/popup-window.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-grant-application',
  imports: [FormsModule, ReactiveFormsModule, CommonModule, MatDialogModule],
  templateUrl: './grant-application.component.html',
  styleUrl: './grant-application.component.scss'
})
export class GrantApplicationComponent implements OnInit{

  activeUser !: { firstName: string, id: number, isAdmin: boolean, lastName: string, email: string };
  grantForm: FormGroup;
  grantOptions:any[] = [];
  showPopup:boolean = false;
  popupTitle:string = '';
  popupMessage:string = '';

  constructor(private fb: FormBuilder, private grantsService: GrantsService, 
    private authService: AuthService, private toastr: ToastrService, private dialog: MatDialog) {
    this.grantForm = this.fb.group({
      applicantName: [{value:'', disabled: true}, [Validators.required, Validators.minLength(3)]],
      email: [{value:'', disabled: true}, [Validators.required, Validators.email]],
      organizationName: ['', Validators.required],
      grantName: ['', [Validators.required]],
      budget: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      projectDescription: ['', [Validators.required, Validators.minLength(20)]],
      specialAward: [false],
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
    if(this.authService.isLoggedAsAdmin()){
      this.toastr.info(`Admins are not allowed to apply for grants`, 'Info',  {
        progressBar: true, closeButton: true 
      });
      return;
    }
    if (this.grantForm.valid) {
      console.log('Grant Application Submitted:', this.grantForm.value);
      let requestBody = this.getRequestBody(this.grantForm.value);
      this.grantsService.postGrantApplications('',requestBody).subscribe((res)=>{
        console.log(res);
        this.openPopup(res.applicationId);
      }, (err)=>{
        console.log(err);
        this.toastr.error(`The application is submission failed`, 'Error',  {
          progressBar: true, closeButton: true 
        });
      });
      this.grantForm.reset();
      this.ngOnInit();
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
      projectDescription: grantApplicationForm.projectDescription,
      // specialAward: grantApplicationForm.specialAward
    };
  }

  openPopup(applicationId: number){
    this.dialog.open(PopupWindowComponent, {
      width: '400px',
      data: { title: 'Application Submitted!', message: 'Your application has been submitted successfully, Reference number is: ' + applicationId }
    });
  }

}