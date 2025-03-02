import { Component, OnInit } from '@angular/core';
import { GrantsService } from '../../grants/services/grants.service';
import { ActivatedRoute, Router } from '@angular/router';
 
@Component({
  selector: 'app-admin-approval',
  imports: [],
  templateUrl: './admin-approval.component.html',
  styleUrl: './admin-approval.component.scss'
})
export class AdminApprovalComponent implements OnInit {
 
  constructor(private grantsService: GrantsService, private router: ActivatedRoute){
 
  }
 
  id: any;
  name: any;
  email: any;
  organization_Name: any;
  selected_Grant: any;
  requested_Amount: any;
 
  ngOnInit(){
    this.router.params.subscribe(params => {
      this.id = params['id'];
      });
    this.grantsService.getGrantApplicationsById(this.id).subscribe((x:any)=> console.log(x));
    this.grantsService.updateGrantApplications('','').subscribe(x=> console.log(x));
  }
 
}