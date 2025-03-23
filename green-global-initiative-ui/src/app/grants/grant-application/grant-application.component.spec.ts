import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GrantApplicationComponent } from './grant-application.component';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { of } from 'rxjs';
import { GrantsService } from '../services/grants.service';
import { AuthService } from '../../authentication/services/auth.service';


class MockGrantsService {
  getGrants() { return of([]); }
  postGrantApplications() { return of({}); }
}

class MockAuthService {
  getUser() { return JSON.stringify({ firstName: 'John', lastName: 'Doe', id: 1, isAdmin: false, email: 'john.doe@example.com' }); }
  isLoggedAsAdmin() { return false; }
}

class MockToastrService {
  info() { }
  success() { }
  error() { }
}

describe('GrantApplicationComponent', () => {
  let component: GrantApplicationComponent;
  let fixture: ComponentFixture<GrantApplicationComponent>;
  let toastrService: MockToastrService;
  let authService: MockAuthService;
  let grantsService: MockGrantsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, ToastrModule.forRoot(), GrantApplicationComponent],
      providers: [
        { provide: GrantsService, useClass: MockGrantsService },
        { provide: AuthService, useClass: MockAuthService },
        { provide: ToastrService, useClass: MockToastrService }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrantApplicationComponent);
    component = fixture.componentInstance;
    toastrService = TestBed.inject(ToastrService) as any;
    authService = TestBed.inject(AuthService) as any;
    grantsService = TestBed.inject(GrantsService) as any;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with default values', () => {
    expect(component.grantForm).toBeDefined();
    expect(component.grantForm.controls['applicantName']).toBeDefined();
    expect(component.grantForm.controls['email']).toBeDefined();
    expect(component.grantForm.controls['organizationName']).toBeDefined();
    expect(component.grantForm.controls['grantName']).toBeDefined();
    expect(component.grantForm.controls['budget']).toBeDefined();
    expect(component.grantForm.controls['projectDescription']).toBeDefined();
  });

  it('should have applicantName field disabled', () => {
    let applicantNameInput = fixture.debugElement.query(By.css('#applicantName')).nativeElement;
    expect(applicantNameInput.disabled).toBeTrue();
  });

  it('should have email field disabled', () => {
    let emailInput = fixture.debugElement.query(By.css('#email')).nativeElement;
    expect(emailInput.disabled).toBeTrue();
  });

  it('should disable name and email fields', () => {
    const nameInput = fixture.debugElement.query(By.css('input[formControlName="applicantName"]')).nativeElement;
    const emailInput = fixture.debugElement.query(By.css('input[formControlName="email"]')).nativeElement;

    expect(nameInput.disabled).toBeTruthy();
    expect(emailInput.disabled).toBeTruthy();
  });

  it('should have a form with all required fields', () => {
    const nameInput = fixture.debugElement.query(By.css('input[formControlName="applicantName"]'));
    const emailInput = fixture.debugElement.query(By.css('input[formControlName="email"]'));
    const organizationInput = fixture.debugElement.query(By.css('input[formControlName="organizationName"]'));
    const grantSelect = fixture.debugElement.query(By.css('select[formControlName="grantName"]'));
    const budgetInput = fixture.debugElement.query(By.css('input[formControlName="budget"]'));
    const descriptionTextarea = fixture.debugElement.query(By.css('textarea[formControlName="projectDescription"]'));

    expect(nameInput).toBeTruthy();
    expect(emailInput).toBeTruthy();
    expect(organizationInput).toBeTruthy();
    expect(grantSelect).toBeTruthy();
    expect(budgetInput).toBeTruthy();
    expect(descriptionTextarea).toBeTruthy();
  });

  it('should disable submit button when form is invalid', () => {
    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
  });

  it('should enable submit button when form is valid', () => {
    component.grantForm.controls['organizationName'].setValue('TechOrg');
    component.grantForm.controls['grantName'].setValue('grant123');
    component.grantForm.controls['budget'].setValue(5000);
    component.grantForm.controls['projectDescription'].setValue('This is a project for testing.');

    fixture.detectChanges();

    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeFalsy();
  });

  it('should show info toastr if the user is an admin', () => {
    spyOn(toastrService, 'info');
    spyOn(authService, 'isLoggedAsAdmin').and.returnValue(true);

    component.onSubmit();

    expect(toastrService.info).toHaveBeenCalled();
  });

  it('should submit the form successfully if it is valid and not an admin', () => {
    spyOn(toastrService, 'success');
    spyOn(grantsService, 'postGrantApplications').and.returnValue(of({}));
    component.grantForm.setValue({
      applicantName: 'John Doe',
      email: 'john.doe@example.com',
      organizationName: 'Org Name',
      grantName: '1',
      budget: '1000',
      projectDescription: 'Description of the project.'
    });

    component.onSubmit();

    expect(grantsService.postGrantApplications).toHaveBeenCalled();
    expect(toastrService.success).toHaveBeenCalled();
  });
});
