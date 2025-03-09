import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDashboardComponent } from './admin-dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { GrantsService } from '../../grants/services/grants.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { of } from 'rxjs';

describe('AdminDashboardComponent', () => {
  let component: AdminDashboardComponent;
  let fixture: ComponentFixture<AdminDashboardComponent>;
  let grantsService: jasmine.SpyObj<GrantsService>;
  let router: Router;

  beforeEach(async () => {
    const grantServiceSpy = jasmine.createSpyObj('GrantsService', ['getGrantApplications']);
    grantServiceSpy.getGrantApplications.and.returnValue(of([]));
    await TestBed.configureTestingModule({
      imports: [AdminDashboardComponent, HttpClientModule, MatPaginatorModule, MatSortModule, MatTableModule],
      providers: [
        provideAnimationsAsync(),
        { provide: GrantsService, useValue: grantServiceSpy },
        {
          provide: ActivatedRoute,
          useValue: { params: of({ id: '123' }) }
        },
      ] 
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDashboardComponent);
    component = fixture.componentInstance;
    grantsService = TestBed.inject(GrantsService) as jasmine.SpyObj<GrantsService>;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.title).toBe('Admin Dashboard');
    expect(component.ApprovedApps).toBe('');
    expect(component.RejectedApps).toBe('');
    expect(component.PendingApps).toBe('');
  });

  it('should call getGrantApplications on init and update data', () => {
    const mockResponse = [
      {
        "applicationId": 1,
        "organizationName": "ATU",
        "applicationStatus": "Rejected",
        "userDetailsDto": {
            "userId": 1,
            "firstName": "Athul",
            "lastName": "S",
            "email": "athul@gmail.com",
            "isAdmin": false,
            "lastLogin": null,
            "applicationDetails": []
        },
        "grants": {
            "grantId": 1,
            "grantName": "Teto",
            "amount": "10000",
            "description": "For climate change Initiatives",
            "eligibility": "NA",
            "applicationDetails": null
        },
        "approvalDate": "21/01/2024",
        "requestedAmount": "10000",
        "projectDescription": "",
        "adminComments": null
    },
    {
      "applicationId": 2,
      "organizationName": "EY",
      "applicationStatus": "Approved",
      "userDetailsDto": {
          "userId": 2,
          "firstName": "Vinod",
          "lastName": "Vijayan",
          "email": "vinod@gmail.com",
          "isAdmin": false,
          "lastLogin": null,
          "applicationDetails": []
      },
      "grants": {
          "grantId": 2,
          "grantName": "Pejite Innovation",
          "amount": "5000",
          "description": "For climate change Initiatives",
          "eligibility": "NA",
          "applicationDetails": null
      },
      "approvalDate": null,
      "requestedAmount": "6000",
      "projectDescription": "This is a Group project for green global initiative POC",
      "adminComments": null
    }];
    grantsService.getGrantApplications.and.returnValue(of(mockResponse));
    component.ngOnInit();

    expect(grantsService.getGrantApplications).toHaveBeenCalledWith('grants', '');
    expect(component.ApprovedApps).toBe('1');
    expect(component.RejectedApps).toBe('1');
    expect(component.PendingApps).toBe('');
    expect(component.cards[1].value).toBe('1');
    expect(component.cards[2].value).toBe('0');
    expect(component.cards[3].value).toBe('1');
    expect(component.dataSource.data.length).toBe(2);
  });

  it('should navigate to application approval page on selection', () => {
    spyOn(router, 'navigate');
    const row = { id: 1 };
    component.onApplicationSelection(row);
    expect(router.navigate).toHaveBeenCalledWith(['/admin/application-approval', row.id]);
  });
});
