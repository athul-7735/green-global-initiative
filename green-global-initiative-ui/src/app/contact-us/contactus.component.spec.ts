import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ContactUsComponent } from './contactus.component';
import { By } from '@angular/platform-browser';
import { ToastrModule, ToastrService } from 'ngx-toastr';  // Import ToastrModule for ToastrService
import { ContactusService } from './services/contactus.service';
import { AuthService } from '../authentication/services/auth.service';
import { of } from 'rxjs';

describe('ContactUsComponent', () => {
  let component: ContactUsComponent;
  let fixture: ComponentFixture<ContactUsComponent>;
  let contactService: jasmine.SpyObj<ContactusService>;
  let authService: jasmine.SpyObj<AuthService>;
  let toastrService: jasmine.SpyObj<ToastrService>;

  beforeEach(async () => {
    const contactServiceSpy = jasmine.createSpyObj('ContactusService', ['postQuery']);
    const authServiceSpy = jasmine.createSpyObj('AuthService', ['removeItem']);
    const toastrServiceSpy = jasmine.createSpyObj('ToastrService', ['error', 'success']);

    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,  // For reactive forms
        HttpClientModule,     // Import HttpClientModule for HttpClient service
        ToastrModule.forRoot(), // Provide ToastrModule for ToastrService
        ContactUsComponent     // Your component that depends on HttpClient
      ],
      providers: [
        { provide: ContactusService, useValue: contactServiceSpy },
        { provide: AuthService, useValue: authServiceSpy },
        { provide: ToastrService, useValue: toastrServiceSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ContactUsComponent);
    component = fixture.componentInstance;
    contactService = TestBed.inject(ContactusService) as jasmine.SpyObj<ContactusService>;
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    toastrService = TestBed.inject(ToastrService) as jasmine.SpyObj<ToastrService>;
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

  it('should show error toastr when form is invalid', () => {
    component.contactUsForm.setErrors({ invalid: true }); // Simulates an invalid form
  
    component.onSubmit();
  
    expect(toastrService.error).toHaveBeenCalledWith(
      'Please fill out all required fields correctly.',
      'Error',
      jasmine.objectContaining({ progressBar: true, closeButton: true })
    );
  });

  it('should remove authentication item before submission', () => {
    component.contactUsForm.setValue({
      name: 'John Doe',
      email: 'johndoe@example.com',
      phone: '1234567890',
      message: 'Hello, I need support.'
    });

    contactService.postQuery.and.returnValue(of({ success: true }));

    component.onSubmit();

    expect(authService.removeItem).toHaveBeenCalled();
  });

  it('should call postQuery service when form is valid', () => {
    component.contactUsForm.setValue({
      name: 'John Doe',
      email: 'johndoe@example.com',
      phone: '1234567890',
      message: 'Hello, I need support.'
    });

    contactService.postQuery.and.returnValue(of({ success: true }));

    component.onSubmit();

    expect(contactService.postQuery).toHaveBeenCalled();
  });

  it('should show success toastr on successful query submission', () => {
    component.contactUsForm.setValue({
      name: 'John Doe',
      email: 'johndoe@example.com',
      phone: '1234567890',
      message: 'Hello, I need support.'
    });

    contactService.postQuery.and.returnValue(of({ success: true }));

    component.onSubmit();

    expect(toastrService.success).toHaveBeenCalledWith(
      'Query submitted successfully',
      'Success',
      jasmine.objectContaining({ progressBar: true, closeButton: true, timeOut: 5000 })
    );
  });
  
});
