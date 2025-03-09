import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactUsComponent } from './contactus.component';
import { By } from '@angular/platform-browser';

describe('ContactUsComponent', () => {
  let component: ContactUsComponent;
  let fixture: ComponentFixture<ContactUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactUsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display correct heading text', () => {
    const heading = fixture.debugElement.query(By.css('.heading h1')).nativeElement;
    const subheading = fixture.debugElement.query(By.css('.heading h2')).nativeElement;

    expect(heading.textContent).toContain('Contact Us');
    expect(subheading.textContent).toContain('Send Your Enquiry');
  });

  it('should have form input fields', () => {
    const nameInput = fixture.debugElement.query(By.css('input#name')).nativeElement;
    const emailInput = fixture.debugElement.query(By.css('input#email')).nativeElement;
    const phoneInput = fixture.debugElement.query(By.css('input#phone')).nativeElement;
    const messageInput = fixture.debugElement.query(By.css('input#message')).nativeElement;

    expect(nameInput).toBeTruthy();
    expect(emailInput).toBeTruthy();
    expect(phoneInput).toBeTruthy();
    expect(messageInput).toBeTruthy();
  });

  it('should have a submit button', () => {
    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton).toBeTruthy();
  });
  
});
