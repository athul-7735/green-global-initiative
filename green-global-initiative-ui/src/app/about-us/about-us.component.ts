import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
 
@Component({
  selector: 'app-aboutus',
  imports: [CommonModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.scss'
})
export class AboutusComponent {
  title: string = 'About Us';
  sections: { heading: string; content: string[] }[] = [
    {
      heading: 'MISSION ON',
      content: [
        'To Empower communities through innovative green solutions for a sustainable,harmonious planet.'
      ]
    },
    {
      heading: 'VISION ON',
      content: [
        'To Inspire Global action for environmental conservation and thriving green future.'
      ]
    },
    {
      heading: 'CORE VALUES',
      content: [
        'Sustainability, innovation, community empowerment,environmental stewardship, and collective action.'
      ]
    },
    {
      heading: 'ACHEIVEMENTS',
      content: [
        'Implemented impactful green initiatives, advanced eco-technologies, and fostered global partnerships for a sustainable future.'
      ]
    }
  ];
}
 
 