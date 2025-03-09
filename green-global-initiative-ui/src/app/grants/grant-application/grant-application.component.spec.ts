import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrantApplicationComponent } from './grant-application.component';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';

describe('GrantApplicationComponent', () => {
  let component: GrantApplicationComponent;
  let fixture: ComponentFixture<GrantApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrantApplicationComponent, HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrantApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should initialize the form with default values', () => {
    expect(component.grantForm).toBeDefined();
    expect(component.grantForm.controls['applicantName']).toBeDefined();
    expect(component.grantForm.controls['email']).toBeDefined();
    expect(component.grantForm.controls['organizationName']).toBeDefined();
    expect(component.grantForm.controls['grantName']).toBeDefined();
    expect(component.grantForm.controls['budget']).toBeDefined();
    expect(component.grantForm.controls['projectDescription']).toBeDefined();
  });
  it('should have applicantName field disabled', () => {
    let applicantNameInput = fixture.debugElement.query(By.css('#applicantName')).nativeElement;
    expect(applicantNameInput.disabled).toBeTrue();
  });
  it('should have email field disabled', () => {
    let emailInput = fixture.debugElement.query(By.css('#email')).nativeElement;
    expect(emailInput.disabled).toBeTrue();
  });
  it('should disable name and email fields', () => {
    const nameInput = fixture.debugElement.query(By.css('input[formControlName="applicantName"]')).nativeElement;
    const emailInput = fixture.debugElement.query(By.css('input[formControlName="email"]')).nativeElement;

    expect(nameInput.disabled).toBeTruthy();
    expect(emailInput.disabled).toBeTruthy();
  });
  it('should have a form with all required fields', () => {
    const nameInput = fixture.debugElement.query(By.css('input[formControlName="applicantName"]'));
    const emailInput = fixture.debugElement.query(By.css('input[formControlName="email"]'));
    const organizationInput = fixture.debugElement.query(By.css('input[formControlName="organizationName"]'));
    const grantSelect = fixture.debugElement.query(By.css('select[formControlName="grantName"]'));
    const budgetInput = fixture.debugElement.query(By.css('input[formControlName="budget"]'));
    const descriptionTextarea = fixture.debugElement.query(By.css('textarea[formControlName="projectDescription"]'));

    expect(nameInput).toBeTruthy();
    expect(emailInput).toBeTruthy();
    expect(organizationInput).toBeTruthy();
    expect(grantSelect).toBeTruthy();
    expect(budgetInput).toBeTruthy();
    expect(descriptionTextarea).toBeTruthy();
  });
  it('should disable submit button when form is invalid', () => {
    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
  });
  it('should enable submit button when form is valid', () => {
    component.grantForm.controls['organizationName'].setValue('TechOrg');
    component.grantForm.controls['grantName'].setValue('grant123');
    component.grantForm.controls['budget'].setValue(5000);
    component.grantForm.controls['projectDescription'].setValue('This is a project for testing.');

    fixture.detectChanges();

    const submitButton = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(submitButton.disabled).toBeFalsy();
  });
  // it('should show validation message if required fields are empty', () => {
  //   component.grantForm.controls['organizationName'].markAsTouched();
  //   component.grantForm.controls['projectDescription'].markAsTouched();

  //   fixture.detectChanges();

  //   const errorMessageOrg = fixture.debugElement.query(By.css('div:has(small:contains("Organization Name is required"))'));
  //   const errorMessageDesc = fixture.debugElement.query(By.css('div:has(small:contains("Description is required"))'));

  //   expect(errorMessageOrg).toBeTruthy();
  //   expect(errorMessageDesc).toBeTruthy();
  // });
  it('should show error if description is less than 20 characters', () => {
    const descriptionControl = component.grantForm.controls['projectDescription'];
    
    // Set a short description
    descriptionControl.setValue('Short text');
    descriptionControl.markAsTouched();
    fixture.detectChanges();
  
    // Query all <small> elements inside the project description div
    const errorMessages = fixture.debugElement.queryAll(By.css('div:has(textarea) small'));
  
    // Convert them to text values
    const errorTexts = errorMessages.map(el => el.nativeElement.textContent.trim());
  
    // Check if the specific error message is present
    expect(errorTexts).toContain('Description must be at least 20 characters');
  });
  
});
