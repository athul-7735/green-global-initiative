import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  title = 'Nausicaä Global Green Initiative';
  menuIcons = ['Home', 'About', 'Grants', 'My Profile', 'SignIn/SignUp'];
  isAuth =  false;

  constructor(private authService: AuthService){
    this.authService.authStatus$.subscribe((status:boolean)=>{
      if(status){
        this.isAuth = true;
        console.log('Auth Status changed to true');
      }
      else{
        this.isAuth = false;
        console.log('Auth Status changed to false');
      }
    })
  }

  login(){

  }

  logout(){
    this.authService.logout();
  }

}
