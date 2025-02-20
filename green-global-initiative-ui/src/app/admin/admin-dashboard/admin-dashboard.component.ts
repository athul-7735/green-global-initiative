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
}

export interface UserData {
  id: string;
  name: string;
  progress: string;
  fruit: string;
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
      bg_color: '#C4C4C4'
    },
    {
      heading:'Approved Applications',
      value: '0',
      bg_color: '#4B9C06'
    },
    {
      heading:'Rejected Applications', 
      value: '0', 
      bg_color: '#B81111'
    },
    {
      heading:'Pending Applications', 
      value: '0', 
      bg_color: '#E0F617'
    },
  ];

  ngOnInit() {
    this.grantsService.getGrantApplications('grants','').subscribe((response) => {
      console.log('Grants:', response);
      let applicationList: any[] = [];
      this.totalApps = response.length + 1;
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
          name: response[i].userDetails.first_name + ' ' + response[i].userDetails.last_name,
          email: response[i].userDetails.email,
          grant: response[i].grants.grantName,
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
  
  displayedColumns: string[] = ['id', 'name', 'email', 'grant', 'date', 'action'];
  dataSource: MatTableDataSource<GrantData>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onApplicationSelection(row:any) {
    console.log('Selected Application:', row);
    this.router.navigate(['/home'])
  }
}