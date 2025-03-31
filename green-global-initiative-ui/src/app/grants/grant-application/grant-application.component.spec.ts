import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { GrantApplicationComponent } from './grant-application.component';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';
import { ComponentType, ToastrModule, ToastrService } from 'ngx-toastr';
import { of, throwError } from 'rxjs';
import { GrantsService } from '../services/grants.service';
import { AuthService } from '../../authentication/services/auth.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TemplateRef } from '@angular/core';


// class MockGrantsService {
//   getGrants() { return of([]); }
//   postGrantApplications() { return of({}); }
// }

// class MockAuthService {
//   getUser() { return JSON.stringify({ firstName: 'John', lastName: 'Doe', id: 1, isAdmin: false, email: 'john.doe@example.com' }); }
//   isLoggedAsAdmin() { return false; }
// }

// class MockToastrService {
//   info() { }
//   success() { }
//   error() { }
// }

class GrantsServiceStub {
  getGrants() {
    return of([{ id: 1, name: 'Climate Action Grant' }]); // Example mock data
  }
  postGrantApplications(url: string, body: any) {
    return of({ applicationId: 123 }); // Simulating a successful response
  }
}

class AuthServiceStub {
  getUser(): string | null {
    return JSON.stringify({
      firstName: 'John', lastName: 'Doe', id: 1, isAdmin: false, email: 'john.doe@example.com'
    });
  }
  isLoggedAsAdmin() {
    return false;
  }
}

class ToastrServiceStub {
  info(message: string, title?: string) {}
  error(message: string, title?: string) {}
}

class MatDialogStub {
  open<T, D = any, R = any>(
    component: ComponentType<T> | TemplateRef<any>,
    config?: MatDialogConfig<D>
  ): MatDialogRef<T, R> {
    // Provide the stub implementation here
    return {} as MatDialogRef<T, R>;
  }
}

describe('GrantApplicationComponent', () => {

  let component: GrantApplicationComponent;
  let fixture: ComponentFixture<GrantApplicationComponent>;
  let grantsService: GrantsServiceStub;
  let authService: AuthServiceStub;
  let toastr: ToastrServiceStub;
  let matDialog: MatDialogStub;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, ToastrModule.forRoot(), GrantApplicationComponent, FormsModule, ReactiveFormsModule],
      providers: [
        { provide: GrantsService, useClass: GrantsServiceStub },
        { provide: AuthService, useClass: AuthServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
        { provide: MatDialog, useClass: MatDialogStub }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrantApplicationComponent);
    component = fixture.componentInstance;
    grantsService = TestBed.inject(GrantsService);
    authService = TestBed.inject(AuthService);
    toastr = TestBed.inject(ToastrService);
    matDialog = TestBed.inject(MatDialog);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with correct user data', () => {
    expect(component.grantForm.controls['applicantName'].value).toBe('John Doe');
    expect(component.grantForm.controls['email'].value).toBe('john.doe@example.com');
  });

  it('should disable name and email fields', () => {
    expect(component.grantForm.controls['applicantName'].disabled).toBeTrue();
    expect(component.grantForm.controls['email'].disabled).toBeTrue();
  });

  it('should validate form correctly', () => {
    component.grantForm.controls['organizationName'].setValue('');
    component.grantForm.controls['grantName'].setValue('');
    component.grantForm.controls['budget'].setValue('abc');
    component.grantForm.controls['projectDescription'].setValue('Short');

    expect(component.grantForm.valid).toBeFalse();

    component.grantForm.controls['organizationName'].setValue('Tech Corp');
    component.grantForm.controls['grantName'].setValue('1');
    component.grantForm.controls['budget'].setValue('5000');
    component.grantForm.controls['projectDescription'].setValue('This is a valid description with more than 20 characters.');

    expect(component.grantForm.valid).toBeTrue();
  });

  it('should prevent admin users from submitting the form', () => {
    spyOn(authService, 'isLoggedAsAdmin').and.returnValue(true);

    component.onSubmit();

    expect(toastr.info).toHaveBeenCalled();
  });

  // it('should call grantsService.postGrantApplications on valid submission', () => {
  //   spyOn(authService, 'isLoggedAsAdmin').and.returnValue(false);
    
  //   component.grantForm.controls['organizationName'].setValue('Tech Corp');
  //   component.grantForm.controls['grantName'].setValue('1');
  //   component.grantForm.controls['budget'].setValue('5000');
  //   component.grantForm.controls['projectDescription'].setValue('This is a valid description.');

  //   const responseMock = { applicationId: 123 };
  //   spyOn(grantsService, 'postGrantApplications').and.returnValue(of(responseMock));
  //   spyOn(matDialog, 'open');

  //   component.onSubmit();

  //   expect(grantsService.postGrantApplications).toHaveBeenCalled();
  //   expect(matDialog.open).toHaveBeenCalledWith(jasmine.any(Function), {
  //     width: '400px',
  //     data: { title: 'Application Submitted!', message: 'Your application has been submitted successfully, Reference number is: 123' }
  //   });
  // });

  it('should show error message on failed submission', () => {
    spyOn(authService, 'isLoggedAsAdmin').and.returnValue(false);

    component.grantForm.controls['organizationName'].setValue('Tech Corp');
    component.grantForm.controls['grantName'].setValue('1');
    component.grantForm.controls['budget'].setValue('5000');
    component.grantForm.controls['projectDescription'].setValue('Valid project description.');

    spyOn(grantsService, 'postGrantApplications').and.returnValue(throwError(() => new Error('Submission failed')));
    spyOn(toastr, 'error');

    component.onSubmit();

    expect(toastr.error).toHaveBeenCalled();
  });
});
