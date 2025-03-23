import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HeaderComponent } from './header.component';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { AuthService } from '../../authentication/services/auth.service';
import { CommonModule } from '@angular/common';

class AuthServiceStub {
  authStatus$ = of(true); // Simulating an authenticated user
  getUser() {
    return { id: 1, name: 'Test User' };
  }
  isLoggedAsAdmin() {
    return true;
  }
  logout() {}
}

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let authServiceStub: AuthServiceStub;
  let toastrService: ToastrService;
  let router: Router;
  
  beforeEach(async () => {
    authServiceStub = new AuthServiceStub();

    await TestBed.configureTestingModule({
      imports: [HeaderComponent,
        RouterTestingModule.withRoutes([]),
        ToastrModule.forRoot(),
        CommonModule
      ],
      providers: [ 
        { provide: AuthService, useValue: authServiceStub },
        ToastrService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    toastrService = TestBed.inject(ToastrService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should update isAuth when authStatus$ emits true', () => {
    expect(component.isAuth).toBeTrue();
  });

  it('should navigate to /admin if user is an admin', () => {
    spyOn(router, 'navigate');
    component.onHomeNavigate();
    expect(router.navigate).toHaveBeenCalledWith(['/admin']);
  });

  it('should navigate to /home if user is not an admin', () => {
    const navigateSpy = spyOn(router, 'navigate');
    authServiceStub.isLoggedAsAdmin = () => false;
    component.onHomeNavigate();
    expect(navigateSpy).toHaveBeenCalledWith(['/admin']);
  });

  it('should call logout and show toastr message', () => {
    spyOn(authServiceStub, 'logout');
    spyOn(toastrService, 'success');

    component.isAuth = true;
    component.logout();

    expect(authServiceStub.logout).toHaveBeenCalled();
    expect(toastrService.success).toHaveBeenCalledWith(
      'Logout Successfull',
      'Success',
      jasmine.objectContaining({ progressBar: true, closeButton: true })
    );
  });

});