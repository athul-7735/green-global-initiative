import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let router: Router;
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeComponent, RouterTestingModule],
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize section texts correctly', () => {
    expect(component.section1Text).toBe('We’re committed to supporting initiatives that combat climate change and foster a sustainable planet.');
    expect(component.section1SubText).toBe('As part of Nausicaä’s Global Green’s sustainable planet goals, we unite global efforts to develop innovative ideas and provide grants that drive impactful climate change initiatives.');
    expect(component.section2Text).toBe('The Nausicaä Global Green Initiative boldly invests in creative solutions to critical challenges, sparking hope for our future.');
    expect(component.section3Text).toBe('Grantees are Reimagining the world in Their Own Vision');
  });

  it('should navigate to About Us page', () => {
    spyOn(router, 'navigate');

    component.navigateToAboutUsPage();

    expect(router.navigate).toHaveBeenCalledWith(['about-us']);
  });

  it('should navigate to Grants page', () => {
    spyOn(router, 'navigate');

    component.navigateToGrantsPage();

    expect(router.navigate).toHaveBeenCalledWith(['grants']);
  });
});
