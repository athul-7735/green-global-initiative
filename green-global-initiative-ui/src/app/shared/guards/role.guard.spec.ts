import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { roleGuard } from './role.guard';
import { AuthService } from '../../authentication/services/auth.service';
import { of } from 'rxjs';
import { Injector, runInInjectionContext } from '@angular/core';

describe('roleGuard', () => {
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;
  let injector: Injector;
  let route: ActivatedRouteSnapshot;
  let state: RouterStateSnapshot;

  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthService', ['getUser']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: authService },
        { provide: Router, useValue: router }
      ]
    });

    injector = TestBed.inject(Injector);
    route = new ActivatedRouteSnapshot();
    state = {} as RouterStateSnapshot;
  });

  it('should allow access if user is authenticated and isAdmin is true', () => {
    const mockUser = JSON.stringify({ isAdmin: true });
    authService.getUser.and.returnValue(mockUser);

    const result = runInInjectionContext(injector, () => roleGuard(route, state));

    expect(result).toBeTrue();
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should deny access and navigate to login if user is not authenticated', () => {
    authService.getUser.and.returnValue(null);

    const result = runInInjectionContext(injector, () => roleGuard(route, state));

    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should deny access and navigate to login if user is not an admin', () => {
    const mockUser = JSON.stringify({ isAdmin: false });
    authService.getUser.and.returnValue(mockUser);

    const result = runInInjectionContext(injector, () => roleGuard(route, state));

    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
