import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';
import * as crypto from 'crypto-js';
import * as jwt_decode from 'jwt-decode';
 
describe('AuthService', () => {
  let service: AuthService;
  let mockStorage: { [key: string]: string } = {};
 
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
    spyOn(localStorage, 'setItem').and.callFake((key: string, value: string) => {
      mockStorage[key] = value;
    });
    spyOn(localStorage, 'getItem').and.callFake((key: string) => mockStorage[key] || null);
    spyOn(localStorage, 'removeItem').and.callFake((key: string) => {
      delete mockStorage[key];
    });
    spyOn(sessionStorage, 'setItem').and.callFake((key: string, value: string) => {
      mockStorage[key] = value;
    });
    spyOn(sessionStorage, 'getItem').and.callFake((key: string) => mockStorage[key] || null);
    spyOn(sessionStorage, 'removeItem').and.callFake((key: string) => {
      delete mockStorage[key];
    });
  });
 
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
 
  it('should hash password correctly', () => {
    const password = 'test123';
    const salt = 'randomSalt';
    const expectedHash = crypto.SHA256(password + salt).toString(crypto.enc.Base64);
    expect(service.hashPassword(password, salt)).toEqual(expectedHash);
  });
 
  it('should verify password correctly', () => {
    const password = 'test123';
    const salt = 'randomSalt';
    const hash = service.hashPassword(password, salt);
    expect(service.verifyPassword(password, hash, salt)).toBeTrue();
  });
 
  it('should manage authentication state', () => {
    let isAuthenticated: boolean | undefined;
    service.authStatus$.subscribe(status => isAuthenticated = status);
    service.login();
    expect(isAuthenticated).toBeTrue();
    service.logout();
    expect(isAuthenticated).toBeFalse();
  });
 
  it('should store and retrieve JWT token', () => {
    service.setItem('mockToken');
    expect(service.getToken()).toEqual('mockToken');
  });
 
  it('should remove JWT token', () => {
    service.setItem('mockToken');
    service.removeItem();
    expect(service.getToken()).toEqual('');
  });
 
  it('should store and retrieve user session', () => {
    const user = { id: 1, name: 'John Doe' };
    service.setUser(user);
    expect(JSON.parse(service.getUser() || '{}')).toEqual(user);
  });
 
  it('should determine if token is expired', () => {
    let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    service.isTokenExpired(token);
    expect(service.isTokenExpired(token)).toBeFalse();
  });
 
  it('should determine if user is admin', () => {
    service.setUser(JSON.stringify({ isAdmin: true }));
    expect(service.isLoggedAsAdmin()).not.toBeNull();
    service.setUser(JSON.stringify({ isAdmin: false }));
    expect(service.isLoggedAsAdmin()).toBeFalse();
  });
});
