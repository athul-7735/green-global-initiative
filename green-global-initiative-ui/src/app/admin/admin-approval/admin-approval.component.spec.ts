import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminApprovalComponent } from './admin-approval.component';
import { ActivatedRoute } from '@angular/router';
import { of, throwError } from 'rxjs';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { GrantsService } from '../../grants/services/grants.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 
class MockGrantsService {
  getGrantApplicationsById(id: string) {
    return of([
      {
        userDetailsDto: { firstName: 'John', lastName: 'Doe', email: 'john.doe@example.com' },
        organizationName: 'Tech Org',
        grants: { grantName: 'Climate Fund', amount: 5000 },
        applicationStatus: 'In Progress',
        adminComments: ''
      }
    ]);
  }
 
  updateGrantApplications(url: string, requestBody: any) {
    return of({
      applicationStatus: requestBody.applicationStatus,
      adminComments: requestBody.adminComments
    });
  }
}
 
// Mocking ActivatedRoute
const mockActivatedRoute = {
  params: of({ id: '123' })  // Simulate route parameter
};
 
// Mocking ToastrService
class mockToastrService {
  success = jasmine.createSpy('success');
  error = jasmine.createSpy('error');
}
 
describe('AdminApprovalComponent', () => {
  let component: AdminApprovalComponent;
  let fixture: ComponentFixture<AdminApprovalComponent>;
  let grantsService: GrantsService;
  let toastrService: ToastrService;
 
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminApprovalComponent,
        HttpClientTestingModule,
        ToastrModule.forRoot(),
        CommonModule,
        FormsModule, 
        ReactiveFormsModule
      ],
      providers:[
        { provide: GrantsService, useValue: new MockGrantsService()  },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: ToastrService, useValue: new mockToastrService() }
      ]
    })
    .compileComponents();
  });
 
  beforeEach(() => {
    fixture = TestBed.createComponent(AdminApprovalComponent);
    component = fixture.componentInstance;
    grantsService = TestBed.inject(GrantsService);
    toastrService = TestBed.inject(ToastrService);
    fixture.detectChanges();
  });
 
  it('should create', () => {
    expect(component).toBeTruthy();
  });
 
  it('should initialize with grant application data', () => {
    expect(component.id).toBe('123');
    expect(component.name).toBe('John Doe');
    expect(component.organization_Name).toBe('Tech Org');
    expect(component.email).toBe('john.doe@example.com');
    expect(component.selected_Grant).toBe('Climate Fund');
    expect(component.requested_Amount).toBe(5000);
    expect(component.applicationStatus).toBe('In Progress');
    expect(component.grantReviewForm.controls['adminComments'].value).toBe('');
    expect(component.enableEditing).toBeTrue();
  });
 
  it('should not enable editing if status is not "In Progress"', () => {
    const mockData = [
      {
        userDetailsDto: { firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
        organizationName: 'Test Org',
        grants: { grantName: 'Tech Grant', amount: 5000 },
        applicationStatus: 'Approved',
        adminComments: 'Reviewed'
      }
    ];
 
    spyOn(grantsService, 'getGrantApplicationsById').and.returnValue(of(mockData));
 
    component.ngOnInit();
    fixture.detectChanges();
 
    expect(component.enableEditing).toBeFalse();
  });
 
  it('should update application status when approved', () => {
    const mockResponse = { applicationStatus: 'Approved', adminComments: 'Reviewed' };
    spyOn(grantsService, 'updateGrantApplications').and.returnValue(of(mockResponse));
 
    component.id = '123';
    component.organization_Name = 'Test Org';
    component.grantReviewForm.controls['adminComments'].setValue('Reviewed');
 
    component.onSubmit('approve');
    fixture.detectChanges();
    expect(grantsService.updateGrantApplications).toHaveBeenCalled();
    expect(component.applicationStatus).toBe('Approved');
    expect(component.enableEditing).toBeFalse();
    expect(toastrService.success).toHaveBeenCalled();
  });
 
  it('should show error toast on update failure', () => {
    spyOn(grantsService, 'updateGrantApplications').and.returnValue(throwError(() => new Error('Update failed')));
   
 
    component.id = '123';
    component.organization_Name = 'Test Org';
    component.grantReviewForm.controls['adminComments'].setValue('Reviewed');
    component.onSubmit('approve');
    fixture.detectChanges();
 
    expect(toastrService.error).toHaveBeenCalled();
  });
});
 