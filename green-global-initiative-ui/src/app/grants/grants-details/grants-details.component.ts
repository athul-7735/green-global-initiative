import { Component } from '@angular/core';

@Component({
  selector: 'app-grants-details',
  imports: [],
  templateUrl: './grants-details.component.html',
  styleUrl: './grants-details.component.scss'
})
export class GrantsDetailsComponent {

  heading: string = 'Our Grants';
  Description: string = 'Nausicaä’s Global Green Initiative (We) provides a range of multiple grants (including the popular Teto grant) to cater to diverse climate change initiatives. This approach encourages broad participation and supports a variety of innovative projects. Each grant is tailored to specific areas of environmental impact';
  grants: { heading: string; fund: string, focus: string }[] = [
    {
      heading: 'Teto Grant',
      fund: 'Funding: Up to €10,000',
      focus: 'Focus: Grassroots climate action projects aimed at immediate and impactful environmental change.'
    },
    {
      heading: 'Pejite Innovation Grant',
      fund: 'Funding: Up to €25,000',
      focus: 'Focus: Cutting-edge innovations and technologies that offer scalable solutions to climate change.'
    },
    {
      heading: 'Ohmu Biodiversity Grant',
      fund: 'Funding: Up to €15,000',
      focus: 'Focus: Biodiversity preservation, habitat restoration, and projects to protect endangered species.'
    },
  ];
}
