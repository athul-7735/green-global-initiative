import { Component, OnInit, ViewChild } from '@angular/core';
import { GrantsService } from '../../grants/services/grants.service';
import { CommonModule } from '@angular/common';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { Router } from '@angular/router';

export interface card {
  heading: string;
  value: string;
  bg_color: string;
}

export interface GrantData {
  id: string;
  name: string;
  email: string;
  grant: string;
  date: string;
  status: string;
}

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, MatFormFieldModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent implements OnInit {
  title = 'Admin Dashboard';
  totalApps = '';
  ApprovedApps = '';
  RejectedApps='';
  PendingApps='';

  constructor(private grantsService: GrantsService, private router: Router) {
    this.dataSource = new MatTableDataSource();
  }
  
  cards: card[] = [
    {
      heading:'Total Applications', 
      value: '0', 
      bg_color: '#5D6D7E'
    },
    {
      heading:'Approved Applications',
      value: '0',
      bg_color: '#28B463'
    },
    {
      heading:'Rejected Applications', 
      value: '0', 
      bg_color: '#C0392B'
    },
    {
      heading:'Pending Applications', 
      value: '0', 
      bg_color: '#F4A62A'
    },
  ];

  ngOnInit() {
    this.grantsService.getGrantApplications('grants','').subscribe((response) => {
      console.log('Grants:', response);
      let applicationList: any[] = [];
      this.totalApps = response.length;
      for (let i = 0; i < response.length; i++) {
        
        if(response[i].applicationStatus == 'In Progress'){
          if(this.PendingApps == '') this.PendingApps = '1';
          else (Number(this.PendingApps)+1).toString();
        }
        if(response[i].applicationStatus == 'Approved'){
          if(this.ApprovedApps == '') this.ApprovedApps = '1';
          else (Number(this.ApprovedApps)+1).toString();
        }
        if(response[i].applicationStatus == 'Rejected'){
          if(this.RejectedApps == '') this.RejectedApps = '1';
          else (Number(this.RejectedApps)+1).toString();
        }
        applicationList.push({
          id: response[i].applicationId,
          name: response[i].userDetailsDto.firstName + ' ' + response[i].userDetailsDto.lastName,
          email: response[i].userDetailsDto.email,
          grant: response[i].grants.grantName,
          status: response[i].applicationStatus,
          date: response[i].approvalDate,
        });
      }
      this.dataSource = new MatTableDataSource(applicationList);
      this.cards[0].value = this.totalApps?this.totalApps:'0';
      this.cards[1].value = this.ApprovedApps?this.ApprovedApps:'0';
      this.cards[2].value = this.PendingApps?this.PendingApps:'0';
      this.cards[3].value = this.RejectedApps?this.RejectedApps:'0';
    });
  }
  
  displayedColumns: string[] = ['id', 'name', 'email', 'grant', 'date', 'status', 'action'];
  dataSource: MatTableDataSource<GrantData>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  // applyFilter(event: Event) {
  //   const filterValue = (event.target as HTMLInputElement).value;
  //   this.dataSource.filter = filterValue.trim().toLowerCase();

  //   if (this.dataSource.paginator) {
  //     this.dataSource.paginator.firstPage();
  //   }
  // }

  onApplicationSelection(row:any) {
    console.log('Selected Application:', row);
    this.router.navigate(['/admin/application-approval',row.id],)
  }
}