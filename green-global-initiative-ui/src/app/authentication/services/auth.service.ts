import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as crypto from 'crypto-js';
import * as jwt_decode from 'jwt-decode';

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
    this.removeItem();
  }

  login() {
    this.isAuthenticatedSubject.next(true); // Update the value to true when the user logs in
  }

  setItem(token: string){
    localStorage.setItem('JWT_Token', token);
  }

  getToken(): string{
    let token = localStorage.getItem('JWT_Token');
    if(token){
      return token;
    } else {
      return '';
    }
  }

  removeItem(){
    localStorage.removeItem('JWT_Token');
  }

  setUser(user: any) {
    sessionStorage.setItem('user', JSON.stringify(user));
  }
  
  getUser(): string|null{
    return sessionStorage.getItem('user');
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode.jwtDecode(token);
    } catch(Error) {
      return null;
    }
  }

  isTokenExpired(token: string): boolean {
    if (!token) return true;
    try {
      const decodedToken: any = this.getDecodedAccessToken(token);
      const expiryTime = decodedToken.exp * 1000; // Convert to milliseconds
      return Date.now() >= expiryTime;
    } catch (error) {
      return true; // Treat any errors as an expired token
    }
  }

  isLoggedAsAdmin(): boolean {
    let isAdmin= false;
    let userDetails = this.getUser();
    let parsedValue = JSON.parse(userDetails?userDetails:'');
    if(parsedValue.isAdmin){
      isAdmin = true;
    }
    return isAdmin;
  }

}
