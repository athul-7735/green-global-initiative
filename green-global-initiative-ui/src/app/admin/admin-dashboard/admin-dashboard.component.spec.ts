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

// const mockGrantApplications = [
//   {
//     applicationId: '1',
//     userDetails: { first_name: 'John', last_name: 'Doe', email: 'john.doe@example.com' },
//     grants: { grantName: 'Grant A' },
//     approvalDate: '2025-01-01',
//     applicationStatus: 'In Progress'
//   },
//   {
//     applicationId: '2',
//     userDetails: { first_name: 'Jane', last_name: 'Smith', email: 'jane.smith@example.com' },
//     grants: { grantName: 'Grant B' },
//     approvalDate: '2025-01-02',
//     applicationStatus: 'Approved'
//   },
//   {
//     applicationId: '3',
//     userDetails: { first_name: 'Bob', last_name: 'Johnson', email: 'bob.johnson@example.com' },
//     grants: { grantName: 'Grant C' },
//     approvalDate: '2025-01-03',
//     applicationStatus: 'Rejected'
//   }
// ];

// class MockGrantsService {
//   getGrantApplications() {
//     return of(mockGrantApplications);
//   }
// }

// // Mock Router
// class MockRouter {
//   navigate = jasmine.createSpy('navigate');
// }

describe('AdminDashboardComponent', () => {
  let component: AdminDashboardComponent;
  let fixture: ComponentFixture<AdminDashboardComponent>;
  let grantsService: jasmine.SpyObj<GrantsService>;
  let router: Router;

  beforeEach(async () => {
    const grantServiceSpy = jasmine.createSpyObj('GrantsService', ['getGrantApplications']);
    // const applicationServiceMock = {
    //   getApplications: jasmine.createSpy().and.returnValue(of([{ id: 'A1', name: 'Test App' }]))
    // };

    await TestBed.configureTestingModule({
      imports: [AdminDashboardComponent, HttpClientModule, MatPaginatorModule, MatSortModule, MatTableModule],
      providers: [
        provideAnimationsAsync(),
        { provide: GrantsService, useValue: grantServiceSpy },
        {
          provide: ActivatedRoute,
          useValue: { params: of({ id: '123' }) }
        },
        // { provide: grantsService, useValue: applicationServiceMock }
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
    expect(component.totalApps).toBe('');
    expect(component.ApprovedApps).toBe('');
    expect(component.RejectedApps).toBe('');
    expect(component.PendingApps).toBe('');
  });

  it('should call getGrantApplications on init and update data', () => {
    const mockResponse = [
      { applicationId: 1, applicationStatus: 'Approved', userDetails: { first_name: 'John', last_name: 'Doe', email: 'john@example.com' }, grants: { grantName: 'Grant A' }, approvalDate: '2024-02-26' },
      { applicationId: 2, applicationStatus: 'Rejected', userDetails: { first_name: 'Jane', last_name: 'Smith', email: 'jane@example.com' }, grants: { grantName: 'Grant B' }, approvalDate: '2024-02-25' },
      { applicationId: 3, applicationStatus: 'In Progress', userDetails: { first_name: 'Alice', last_name: 'Johnson', email: 'alice@example.com' }, grants: { grantName: 'Grant C' }, approvalDate: '2024-02-24' }
    ];

    grantsService.getGrantApplications.and.returnValue(of(mockResponse));
    
    component.ngOnInit();

    expect(grantsService.getGrantApplications).toHaveBeenCalledWith('grants', '');
    expect(component.totalApps).toBe('4'); // mockResponse.length + 1
    expect(component.ApprovedApps).toBe('1');
    expect(component.RejectedApps).toBe('1');
    expect(component.PendingApps).toBe('1');
    expect(component.cards[0].value).toBe('4'); // Total
    expect(component.cards[1].value).toBe('1'); // Approved
    expect(component.cards[2].value).toBe('1'); // Pending
    expect(component.cards[3].value).toBe('1'); // Rejected
    expect(component.dataSource.data.length).toBe(3);
  });

  it('should filter table data when applyFilter is called', () => {
    component.dataSource = new MatTableDataSource([
      { id: '1', name: 'John Doe', email: 'john@example.com', grant: 'Grant A', date: '2024-02-26' },
      { id: '2', name: 'Jane Smith', email: 'jane@example.com', grant: 'Grant B', date: '2024-02-25' }
    ]);

    const inputEvent = { target: { value: 'Jane' } } as unknown as Event;
    
    component.applyFilter(inputEvent);

    expect(component.dataSource.filter).toBe('jane');
  });

  it('should navigate to application approval page on selection', () => {
    spyOn(router, 'navigate');

    const row = { id: 1 };
    component.onApplicationSelection(row);

    expect(router.navigate).toHaveBeenCalledWith(['/application-approval', row.id]);
  });
});
