import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GrantsDetailsComponent } from './grants-details.component';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

describe('GrantsDetailsComponent', () => {
  let component: GrantsDetailsComponent;
  let fixture: ComponentFixture<GrantsDetailsComponent>;
  let debugElement: DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrantsDetailsComponent], // Since it's standalone, add it to imports instead of declarations
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GrantsDetailsComponent);
    component = fixture.componentInstance;
    debugElement = fixture.debugElement;

    // Mock data
    component.heading = 'Grant Opportunities';
    component.Description = 'Find the right grant for your needs';
    component.grants = [
      { heading: 'Grant 1', fund: '1000 USD', focus: 'Education' },
      { heading: 'Grant 2', fund: '2000 USD', focus: 'Health' },
      { heading: 'Grant 3', fund: '1500 USD', focus: 'Environment' },
    ];

    fixture.detectChanges(); // Ensure data binding updates
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should display heading and description', () => {
    const headingElement = debugElement.query(By.css('h3')).nativeElement;
    const descriptionElement = debugElement.query(By.css('p')).nativeElement;
    expect(headingElement.textContent).toContain('Grant Opportunities');
    expect(descriptionElement.textContent).toContain('Find the right grant for your needs');
  });

  it('should display first grant details', () => {
    const grantHeading = debugElement.query(By.css('.section2-container .left-side-content h2')).nativeElement;
    const grantFund = debugElement.query(By.css('.section2-container .left-side-content p')).nativeElement;
    expect(grantHeading.textContent).toContain('Grant 1');
    expect(grantFund.textContent).toContain('1000 USD');
  });

  it('should render images with correct alt attributes', () => {
    const images = debugElement.queryAll(By.css('img'));
    expect(images.length).toBeGreaterThan(0);
    images.forEach((img) => {
      expect(img.nativeElement.alt).toBe('grants-logo');
    });
  });
});
