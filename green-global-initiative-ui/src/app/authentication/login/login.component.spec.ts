import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { of, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { ApiService } from '../services/api.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: jasmine.SpyObj<AuthService>;
  let apiService: jasmine.SpyObj<ApiService>;
  let toastrService: jasmine.SpyObj<ToastrService>;

  beforeEach(async () => {
    const authSpy = jasmine.createSpyObj('AuthService', ['removeItem', 'hashPassword', 'setItem', 'getDecodedAccessToken', 'setUser', 'login']);
    const apiSpy = jasmine.createSpyObj('ApiService', ['post']);
    const toastrSpy = jasmine.createSpyObj('ToastrService', ['success', 'error']);

    await TestBed.configureTestingModule({
      imports: [LoginComponent, HttpClientModule, ToastrModule.forRoot()],
      providers: [
        { provide: AuthService, useValue: authSpy },
        { provide: ApiService, useValue: apiSpy },
        { provide: ToastrService, useValue: toastrSpy },
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

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    apiService = TestBed.inject(ApiService) as jasmine.SpyObj<ApiService>;
    toastrService = TestBed.inject(ToastrService) as jasmine.SpyObj<ToastrService>;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render form with email and password inputs and submit button', () => {
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('input[formControlName="email"]')).toBeTruthy();
    expect(compiled.querySelector('input[formControlName="password"]')).toBeTruthy();
    expect(compiled.querySelector('button[type="submit"]')).toBeTruthy();
  });

  it('should show error message when login fails with 401 error', () => {
    component.loginForm.setValue({ email: 'test@example.com', password: 'password' });
    apiService.post.and.returnValue(throwError({ status: 401 }));

    component.onSubmit();

    expect(component.errorMessage).toBe('Wrong password/email, please try again!');
    expect(toastrService.error).not.toHaveBeenCalled();
  });

  it('should show error message when login fails with 400 error', () => {
    component.loginForm.setValue({ email: 'test@example.com', password: 'password' });
    apiService.post.and.returnValue(throwError({ status: 400 }));

    component.onSubmit();

    expect(component.errorMessage).toBe('Invalid input, please check your credentials!');
    expect(toastrService.error).not.toHaveBeenCalled();
  });

  it('should show generic error message when login fails with unknown error', () => {
    component.loginForm.setValue({ email: 'test@example.com', password: 'password' });
    apiService.post.and.returnValue(throwError({ status: 500 }));

    component.onSubmit();

    expect(component.errorMessage).toBe('Something went wrong, please try again later!');
    expect(toastrService.error).not.toHaveBeenCalled();
  });

  it('should call AuthService methods and navigate on successful login', () => {
    const mockToken = 'mock-jwt-token';
    const mockUser = { id: 1, firstName: 'John', lastName: 'Doe', email: 'test@example.com', isAdmin: undefined };

    component.loginForm.setValue({ email: 'test@example.com', password: 'password' });

    authService.hashPassword.and.returnValue('hashedPassword');
    apiService.post.and.returnValue(of({ token: mockToken }));
    authService.getDecodedAccessToken.and.returnValue(mockUser);

    component.onSubmit();

    expect(authService.removeItem).toHaveBeenCalled();
    expect(authService.setItem).toHaveBeenCalledWith(mockToken);
    expect(authService.setUser).toHaveBeenCalledWith(mockUser);
    expect(authService.login).toHaveBeenCalled();
    expect(toastrService.success).toHaveBeenCalledWith('Login Successfull', 'Success', jasmine.any(Object));
  });

  it('should show error if form is invalid', () => {
    component.loginForm.setValue({ email: '', password: '' });

    component.onSubmit();

    expect(toastrService.error).toHaveBeenCalledWith('Invalid Form!', 'Error', jasmine.any(Object));
  });
});
