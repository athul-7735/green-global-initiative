import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-footer',
  imports: [MatIconModule, CommonModule, RouterModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {

  constructor(private route:Router){}

  logoText: string = 'Funding Innovation, Empowering Sustainability';
  socialIcons:any[] = [{name:'facebook', src:'assets/images/fb.png'}, {name:'twitter', src:'assets/images/twitter.png'}, {name:'linkedin',src:'assets/images/linkedin.png'}, {name:'instagram',src:'assets/images/insta.png'},{name:'youtube',src:'assets/images/youtube.png'}];
  sections:any[] = [{
    name:'Company', 
    links: [
      {name:'About', link: '/about-us'},
      {name:'FAQ', link: '/about-us'} 
    ]
  },{
    name:'Support', 
    links: [
      {name:'Getting Started', link: '/contact-us'},
      {name:'Help Center', link: '/contact-us'}
    ]
  },{
    name:'Contact Us', 
    links: [
      {name:'globalgreenInitiative@nauicaa.com', link: ''},
      {name:'(414)687-5892', link: ''},
      {name:'794 Mcallister St San Francisco, 94102', link: ''}    
    ]
  }];
  copyright:string = 'Copyright © 2025 nausicaa';
  
}
