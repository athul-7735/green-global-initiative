import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

/**
 * Footer component that displays the Application links.
 */
@Component({
  selector: 'app-footer',
  imports: [MatIconModule, CommonModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {

  logoText: string = 'Funding Innovation, Empowering Sustainability';
  socialIcons:any[] = [{name:'facebook', src:'assets/images/fb.png'}, {name:'twitter', src:'assets/images/twitter.png'}, {name:'linkedin',src:'assets/images/linkedin.png'}, {name:'instagram',src:'assets/images/insta.png'},{name:'youtube',src:'assets/images/youtube.png'}];
  sections:any[] = [{
    name:'Company', 
    links: [
      'About', 'FAQ'
    ]
  },{
    name:'Support', 
    links: [
      'Getting Started', 'Help Center'
    ]
  },{
    name:'Contact Us', 
    links: [
      'Contact@Company.com', '(414)687-5892', '794 Mcallister St San Francisco, 94102'
    ]
  }];
  copyright:string = 'Copyright © 2025 ATU DevOps';
  
}
