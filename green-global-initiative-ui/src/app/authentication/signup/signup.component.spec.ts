import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { ApiService } from '../services/api.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let router: Router;
  let authService: AuthService;
  let apiService: ApiService;
  let toastrService: ToastrService;


  beforeEach(async () => {
    const mockAuthService = jasmine.createSpyObj('AuthService', ['removeItem', 'hashPassword']);
    mockAuthService.hashPassword.and.returnValue('hashedPassword');
    const mockApiService = jasmine.createSpyObj('ApiService', ['post']);
    const mockToastrService = jasmine.createSpyObj('ToastrService', ['success', 'error']);
    const mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    await TestBed.configureTestingModule({
      imports: [SignupComponent, HttpClientModule, ToastrModule.forRoot(), RouterTestingModule.withRoutes([])],
      providers: [
        { provide: AuthService, useValue: mockAuthService },
        { provide: ApiService, useValue: mockApiService },
        { provide: ToastrService, useValue: mockToastrService },
        {
          provide: ActivatedRoute,
          useValue: {
            params: of({ id: '123' }),
            snapshot: { queryParams: {} }
          }
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    authService = TestBed.inject(AuthService);
    apiService = TestBed.inject(ApiService);
    toastrService = TestBed.inject(ToastrService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with default values', () => {
    expect(component.signupForm).toBeDefined();
    expect(component.signupForm.controls['firstName']).toBeDefined();
    expect(component.signupForm.controls['lastName']).toBeDefined();
    expect(component.signupForm.controls['email']).toBeDefined();
    expect(component.signupForm.controls['password']).toBeDefined();
    expect(component.signupForm.controls['confirmPassword']).toBeDefined();
  });

  it('should mark firstName as invalid if empty', () => {
    let firstNameControl = component.signupForm.controls['firstName'];
    firstNameControl.setValue('');
    fixture.detectChanges();
    expect(firstNameControl.invalid).toBeTrue();
  });

  it('should enforce minimum length on firstName', () => {
    let firstNameControl = component.signupForm.controls['firstName'];
    firstNameControl.setValue('Jo'); // Less than 3 characters
    fixture.detectChanges();
    expect(firstNameControl.invalid).toBeTrue();
  });

  it('should mark lastName as invalid if empty', () => {
    let lastNameControl = component.signupForm.controls['lastName'];
    lastNameControl.setValue('');
    fixture.detectChanges();
    expect(lastNameControl.invalid).toBeTrue();
  });

  it('should mark email as invalid if format is incorrect', () => {
    let emailControl = component.signupForm.controls['email'];
    emailControl.setValue('invalid-email'); // Invalid email
    fixture.detectChanges();
    expect(emailControl.invalid).toBeTrue();
  });

  it('should mark email as valid for correct format', () => {
    let emailControl = component.signupForm.controls['email'];
    emailControl.setValue('test@example.com'); // Valid email
    fixture.detectChanges();
    expect(emailControl.valid).toBeTrue();
  });

  it('should require password field', () => {
    let passwordControl = component.signupForm.controls['password'];
    passwordControl.setValue('');
    fixture.detectChanges();
    expect(passwordControl.invalid).toBeTrue();
  });

  it('should enforce minimum length on password', () => {
    let passwordControl = component.signupForm.controls['password'];
    passwordControl.setValue('12345'); // Less than 6 characters
    fixture.detectChanges();
    expect(passwordControl.invalid).toBeTrue();
  });

  it('should mark confirmPassword as invalid if it does not match password', () => {
    component.signupForm.controls['password'].setValue('password123');
    component.signupForm.controls['confirmPassword'].setValue('password456');
    fixture.detectChanges();
    expect(component.signupForm.controls['confirmPassword'].hasError('passwordMismatch')).toBeTrue();
  });

  it('should mark confirmPassword as valid if it matches password', () => {
    component.signupForm.controls['password'].setValue('password123');
    component.signupForm.controls['confirmPassword'].setValue('password123');
    fixture.detectChanges();
    expect(component.signupForm.controls['confirmPassword'].valid).toBeTrue();
  });

  it('should not submit if the form is invalid', () => {
    component.signupForm.setErrors({ invalid: true });
    component.onSubmit();
    expect(apiService.post).not.toHaveBeenCalled();
    expect(toastrService.error).not.toHaveBeenCalled();
  });

  // it('should call API service on valid form submission', () => {
  //   component.signupForm.setErrors(null);
  //   // spyOn(authService, 'removeItem').and.returnValue();
  //   // spyOn(apiService, 'post').and.returnValue({} as any);
  //   // spyOn(toastrService, 'success').and.returnValue({} as any);
  //   // spyOn(router, 'navigate').and.returnValue({} as any);
  //   // const mockResponse = { success: true };
  //   // apiService.post = jasmine.createSpy().and.returnValue(of(mockResponse)); // Directly setting the return value
  //   component.onSubmit();
  //   expect(authService.removeItem).toHaveBeenCalled();
  //   // expect(apiService.post).toHaveBeenCalled();
  //   // expect(toastrService.success).toHaveBeenCalled();
  //   // expect(router.navigate).toHaveBeenCalled();
  // });
});
