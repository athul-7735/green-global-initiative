import { TestBed } from '@angular/core/testing';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';
import { authGuard } from './auth.guard';
import { provideRouter } from '@angular/router';

describe('authGuard', () => {
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthService', ['getUser']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: authService },
        { provide: Router, useValue: router },
        provideRouter([]), // Providing a dummy router for testing
      ],
    });
  });

  function mockRoute(): ActivatedRouteSnapshot {
    return {} as ActivatedRouteSnapshot;
  }

  function mockState(): RouterStateSnapshot {
    return { url: '/test' } as RouterStateSnapshot;
  }

  it('should allow the user to access the route if authenticated', () => {
    authService.getUser.and.returnValue('mockUser'); // Mock user string

    const result = authGuard(mockRoute(), mockState());

    expect(result).toBeTrue(); // Should return true if user is authenticated
    expect(router.navigate).not.toHaveBeenCalled(); // No redirect expected
  });

  it('should redirect to /login if the user is not authenticated', () => {
    authService.getUser.and.returnValue(null); // Simulating unauthenticated user

    const result = authGuard(mockRoute(), mockState());

    expect(result).toBeFalse(); // Should return false for unauthenticated user
    expect(router.navigate).toHaveBeenCalledWith(['/login']); // Expecting redirect
  });
});
