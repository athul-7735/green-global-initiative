import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  constructor(private router:Router) { }

  section1Text: string = 'We’re committed to supporting initiatives that combat climate change and foster a sustainable planet.';
  section1SubText: string = 'As part of Nausicaä’s Global Green’s sustainable planet goals, we unite global efforts to develop innovative ideas and provide grants that drive impactful climate change initiatives.';
  section2Text:string = 'The Nausicaä Global Green Initiative boldly invests in creative solutions to critical challenges, sparking hope for our future.';
  section3Text:string = 'Grantees are Reimagining the world in Their Own Vision';

  navigateToAboutUsPage() {
    this.router.navigate(['about-us']);
  }

  navigateToGrantsPage() {
    this.router.navigate(['grants']);
  }
  
}
