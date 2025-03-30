import { Component, OnInit } from '@angular/core';
import { GrantsService } from '../../grants/services/grants.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-approval',
  imports: [CommonModule],
  templateUrl: './admin-approval.component.html',
  styleUrl: './admin-approval.component.scss'
})
export class AdminApprovalComponent implements OnInit {

  id: any;
  name: any;
  email: any;
  organization_Name: any;
  selected_Grant: any;
  requested_Amount: any;
  applicationStatus: any;
  adminComments: any;
  enableEditing: boolean = false;
  nullComments: boolean = false;

  constructor(private grantsService: GrantsService, private router: ActivatedRoute, private toastr: ToastrService) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.id = params['id'];
      if (this.id) {
        this.fetchGrantData();
      }
    });
  }

  private fetchGrantData() {
    this.grantsService.getGrantApplicationsById(this.id).subscribe((x: any) => {
      console.log(x);
      this.name = x[0].userDetailsDto.firstName + ' ' + x[0].userDetailsDto.lastName;
      this.organization_Name = x[0].organizationName;
      this.email = x[0].userDetailsDto.email;
      this.selected_Grant = x[0].grants.grantName;
      this.requested_Amount = x[0].grants.amount;
      this.applicationStatus = x[0].applicationStatus;
      this.adminComments = x[0].adminComments;
      this.enableEditing = this.applicationStatus == 'In Progress' ? true : false;
    });
  }

  onSubmit(action: String) {
    // if (this.adminComments == null || this.adminComments == '') {
    //   this.nullComments = true;
    // }
    // else {
      let applicationStatus = action == 'approve' ? 'Approved' : 'Rejected';
      let requestBody = {
        applicationId: this.id,
        organizationName: this.organization_Name,
        applicationStatus: applicationStatus,
        adminComments: this.adminComments
      }
      this.grantsService.updateGrantApplications('', requestBody).subscribe(x => {
        console.log(x);
        this.applicationStatus = x.applicationStatus;
        this.adminComments = x.adminComments;
        this.enableEditing = false;
        this.toastr.success(`The application is ${action == 'approve' ? 'Approved' : 'Rejected'}`, 'Success', {
          progressBar: true, closeButton: true
        });
      }, (err) => {
        this.toastr.error(`The application is not ${action == 'approve' ? 'Approved' : 'Rejected'}, Try again later!!`, 'Error', {
          progressBar: true, closeButton: true
        });
        console.log();
      });
    // }
  }
}
