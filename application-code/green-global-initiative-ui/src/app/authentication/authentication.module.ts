import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [],
  imports: [
    LoginComponent,
    CommonModule,
    AuthenticationRoutingModule,
    SignupComponent,
    HttpClientModule
  ],
  exports: [LoginComponent, SignupComponent]
})
export class AuthenticationModule { }
