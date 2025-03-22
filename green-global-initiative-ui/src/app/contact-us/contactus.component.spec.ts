import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ContactUsComponent } from './contactus.component';
import { By } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';  // Import ToastrModule for ToastrService

describe('ContactUsComponent', () => {
  let component: ContactUsComponent;
  let fixture: ComponentFixture<ContactUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,  // For reactive forms
        HttpClientModule,     // Import HttpClientModule for HttpClient service
        ToastrModule.forRoot(), // Provide ToastrModule for ToastrService
        ContactUsComponent     // Your component that depends on HttpClient
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ContactUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with default values', () => {
    expect(component.contactUsForm).toBeDefined();
    expect(component.contactUsForm.controls['name']).toBeDefined();
    expect(component.contactUsForm.controls['email']).toBeDefined();
    expect(component.contactUsForm.controls['phone']).toBeDefined();
    expect(component.contactUsForm.controls['message']).toBeDefined();
  });

  it('should have name field required', () => {
    const nameControl = component.contactUsForm.get('name');
    nameControl?.setValue('');
    expect(nameControl?.valid).toBeFalsy();
  });

  it('should require a valid email', () => {
    const emailControl = component.contactUsForm.get('email');
    emailControl?.setValue('invalid-email');
    expect(emailControl?.valid).toBeFalsy();
    emailControl?.setValue('test@example.com');
    expect(emailControl?.valid).toBeTruthy();
  });

  it('should require a valid phone number', () => {
    const phoneControl = component.contactUsForm.get('phone');
    phoneControl?.setValue('abc123');
    expect(phoneControl?.valid).toBeFalsy();
    phoneControl?.setValue('1234567890');
    expect(phoneControl?.valid).toBeTruthy();
  });

  it('should require message to be at least 10 characters', () => {
    const messageControl = component.contactUsForm.get('message');
    messageControl?.setValue('short');
    expect(messageControl?.valid).toBeFalsy();
    messageControl?.setValue('This is a valid message.');
    expect(messageControl?.valid).toBeTruthy();
  });

  it('should disable submit button when form is invalid', () => {
    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
  });

  it('should enable submit button when form is valid', () => {
    component.contactUsForm.patchValue({
      name: 'Test User',
      email: 'test@example.com',
      phone: '1234567890',
      message: 'This is a valid message.',
    });
    fixture.detectChanges(); // Update the template

    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeFalsy();
  });
  it('should show validation message if name is empty', () => {
    component.contactUsForm.controls['name'].setValue('');
    component.contactUsForm.controls['name'].markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('input[formControlName="name"] + small'));
    expect(errorMessage).toBeTruthy();
    expect(errorMessage.nativeElement.textContent).toContain('Name is required');
  });

  it('should show validation message if message is too short', () => {
    const messageControl = component.contactUsForm.get('message');
    messageControl?.setValue('Short');
    messageControl?.markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('small'));
    expect(errorMessage).toBeTruthy();
    expect(errorMessage.nativeElement.textContent).toContain('Message must be at least 10 characters');
  });
});
