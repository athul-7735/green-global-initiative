import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { roleGuard } from './role.guard';
import { AuthService } from '../../authentication/services/auth.service';
import { of } from 'rxjs';

describe('roleGuard', () => {
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const authServiceSpy = jasmine.createSpyObj('AuthService', ['getUser']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy }
      ]
    });

    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should allow access if user is an admin', () => {
    const mockUser = JSON.stringify({ isAdmin: true });
    authService.getUser.and.returnValue(mockUser);

    const result = roleGuard({ data: { roles: ['admin'] } } as any, {} as any);

    expect(result).toBeTrue();
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should deny access and redirect to login if user is not an admin', () => {
    const mockUser = JSON.stringify({ isAdmin: false });
    authService.getUser.and.returnValue(mockUser);

    const result = roleGuard({ data: { roles: ['admin'] } } as any, {} as any);

    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should deny access and redirect to login if user is null', () => {
    authService.getUser.and.returnValue(null);

    const result = roleGuard({ data: { roles: ['admin'] } } as any, {} as any);

    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
