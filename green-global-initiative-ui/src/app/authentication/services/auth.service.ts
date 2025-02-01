import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as crypto from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  authStatus$ = this.isAuthenticatedSubject.asObservable();

  hashPassword(password: string, salt: string): string {
    const combinedPassword = password + salt;  // Combine password and salt
    return crypto.SHA256(combinedPassword).toString(crypto.enc.Base64); // Hash the combined value
  }

  verifyPassword(rawPassword:string, storedHash:string, salt:string): boolean {
    const enteredHash = this.hashPassword(rawPassword, salt);
    return enteredHash === storedHash; // Compare the newly hashed password with the stored hash
  }

  logout() {
    sessionStorage.removeItem('user');
    this.isAuthenticatedSubject.next(false);
  }

  login() {
    this.isAuthenticatedSubject.next(true); // Update the value to true when the user logs in
  }

  setUser(user: any) {
    sessionStorage.setItem('user', JSON.stringify(user));
  }
}
