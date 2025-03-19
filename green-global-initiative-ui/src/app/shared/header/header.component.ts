import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';
import { ToastrService } from 'ngx-toastr';

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
  isAdmin = false;

  constructor(private authService: AuthService, private toastr: ToastrService, private route: Router){
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
    this.authService.getUser() !== null ? this.isAdmin = this.authService.isLoggedAsAdmin() : this.isAdmin = false;
    this.onHomeNavigate();
  }

  login(){
  }

  onHomeNavigate(){
    if(this.isAdmin){
      this.route.navigate(['/admin']);
      return;
    }
    this.route.navigate(['/home']);
  }

  logout(){
    if(this.isAuth){
      this.authService.logout();
      this.toastr.success('Logout Successfull', 'Success',  {
        progressBar: true, closeButton: true 
      });
    }    
  }

}
