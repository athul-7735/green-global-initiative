import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { FooterComponent } from './shared/footer/footer.component';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './shared/header/header.component';
import { AuthService } from './authentication/services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FooterComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'green-global-initiative-ui';
  
  constructor( private authService: AuthService){ 
    // this.router.events.subscribe((event) => {
    //   if (event instanceof NavigationEnd) {
    //     console.log('NavigationEnd:', event.urlAfterRedirects);
    //   }
    // });

    if(!this.authService.isTokenExpired(this.authService.getToken())){
      this.authService.login();
    };
  }
}
