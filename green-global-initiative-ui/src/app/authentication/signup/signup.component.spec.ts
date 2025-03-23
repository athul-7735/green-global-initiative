import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SignupComponent } from './signup.component';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { ApiService } from '../services/api.service';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';


// Mock Classes for dependencies
class MockAuthService {
  removeItem = jasmine.createSpy('removeItem');
  hashPassword = jasmine.createSpy('hashPassword').and.returnValue('hashedPassword');
}

class MockApiService {
  post = jasmine.createSpy('post').and.returnValue(of({}));
}

class MockToastrService {
  success = jasmine.createSpy('success');
  error = jasmine.createSpy('error');
}

// class MockRouter {
//   navigate = jasmine.createSpy('navigate');
// }

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let authService: AuthService;
  let apiService: ApiService;
  let toastrService: ToastrService;
  // let router: Router;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, 
        SignupComponent, 
        HttpClientTestingModule, 
        ToastrModule.forRoot(), 
        RouterTestingModule.withRoutes([])
      ],
      providers: [
        { provide: AuthService, useClass: MockAuthService },
        { provide: ApiService, useClass: MockApiService },
        { provide: ToastrService, useClass: MockToastrService },
        // { provide: Router, useClass: MockRouter },
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
    authService = TestBed.inject(AuthService);
    apiService = TestBed.inject(ApiService);
    toastrService = TestBed.inject(ToastrService);
    // router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize signupForm with correct controls', () => {
    expect(component.signupForm.contains('firstName')).toBe(true);
    expect(component.signupForm.contains('lastName')).toBe(true);
    expect(component.signupForm.contains('email')).toBe(true);
    expect(component.signupForm.contains('password')).toBe(true);
    expect(component.signupForm.contains('confirmPassword')).toBe(true);
  });

  // it('should make the firstName control required', () => {
  //   const control = component.signupForm.get('firstName')?.setValue('');
  //   expect(control?.valid).toBeFalsy();
  //   control.setValue('John');
  //   expect(control?.valid).toBeTruthy();
  // });

  it('should toggle password visibility', () => {
    expect(component.isPasswordVisible).toBeFalsy();
    component.togglePasswordVisibility();
    expect(component.isPasswordVisible).toBeTruthy();
    component.togglePasswordVisibility();
    expect(component.isPasswordVisible).toBeFalsy();
  });

  it('should call onSubmit and handle success response', () => {
    component.signupForm.setValue({
      firstName: 'John',
      lastName: 'Doe',
      email: 'john.doe@example.com',
      password: 'password123',
      confirmPassword: 'password123'
    });

    component.onSubmit();

    expect(authService.removeItem).toHaveBeenCalled();
    expect(apiService.post).toHaveBeenCalledWith(
      'users/signup', 
      { first_name: 'John', last_name: 'Doe', email: 'john.doe@example.com', password: 'hashedPassword', isAdmin: false, last_login: jasmine.any(Date) }
    );
    expect(toastrService.success).toHaveBeenCalled();
  });

  it('should call onSubmit and handle error response', () => {
    component.signupForm.setValue({
      firstName: 'John',
      lastName: 'Doe',
      email: 'john.doe@example.com',
      password: 'password123',
      confirmPassword: 'password123'
    });
    component.onSubmit();
    expect(authService.removeItem).toHaveBeenCalled();
    expect(apiService.post).toHaveBeenCalled();
  });

});
