import { TestBed } from '@angular/core/testing';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';
import { authGuard } from './auth.guard';
import { provideRouter } from '@angular/router';
import { Injector, runInInjectionContext } from '@angular/core';

describe('authGuard', () => {
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;
  let injector: Injector;
  
  beforeEach(() => {
    // Create Spy Objects for dependencies
    authService = jasmine.createSpyObj('AuthService', ['getUser']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    // Configure TestBed with dependencies
    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: authService },
        { provide: Router, useValue: router }
      ]
    });
    injector = TestBed.inject(Injector);
  });

  it('should allow access if user is authenticated', () => {
    authService.getUser.and.returnValue(`{"username":"testuser","password":"testpassword"}`);

    const result = runInInjectionContext(injector, () => authGuard(null as any, null as any));

    expect(result).toBeTrue();
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should redirect to login if user is not authenticated', () => {
    authService.getUser.and.returnValue(null);

    const result = runInInjectionContext(injector, () => authGuard(null as any, null as any));

    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
