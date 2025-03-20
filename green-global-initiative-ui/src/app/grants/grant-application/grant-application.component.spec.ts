import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GrantApplicationComponent } from './grant-application.component';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';
import { ToastrModule, ToastrService } from 'ngx-toastr';

describe('GrantApplicationComponent', () => {
  let component: GrantApplicationComponent;
  let fixture: ComponentFixture<GrantApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, ToastrModule.forRoot(), GrantApplicationComponent],
    
      providers: [ToastrService]
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

  it('should show validation message if organizationName is empty', () => {
    component.grantForm.controls['organizationName'].setValue('');
    component.grantForm.controls['organizationName'].markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('div:has(small:contains("Organization Name is required"))'));
    expect(errorMessage).toBeTruthy();
  });

  it('should show validation message if projectDescription is empty', () => {
    component.grantForm.controls['projectDescription'].setValue('');
    component.grantForm.controls['projectDescription'].markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('div.project-description-error'));
    console.log(errorMessage);
    // Expect that the validation message is displayed and contains the expected error text
    expect(errorMessage).toBeNull();
    expect(errorMessage.nativeElement.textContent).toContain('Description is required.');
  });

  it('should show error if description is less than 20 characters', () => {
    const descriptionControl = component.grantForm.controls['projectDescription'];
    
    // Set a short description
    descriptionControl.setValue('Short text');
    descriptionControl.markAsTouched();
    fixture.detectChanges();
  
    const errorMessages = fixture.debugElement.queryAll(By.css('div:has(textarea) small'));
    const errorTexts = errorMessages.map(el => el.nativeElement.textContent.trim());
  
    expect(errorTexts).toContain('Description must be at least 20 characters');
  });

  it('should show error message for invalid email', () => {
    const emailControl = component.grantForm.controls['email'];
    emailControl.setValue('invalid-email');
    emailControl.markAsTouched();
    fixture.detectChanges();

   const errorMessage = fixture.debugElement.query(By.css('input[formControlName="email"] + div small'));
    expect(errorMessage).toBeTruthy();
    if (errorMessage) {
      expect(errorMessage.nativeElement.textContent).toContain('Enter a valid email');
    }
  });

  it('should show error if budget is empty', () => {
    const budgetControl = component.grantForm.controls['budget'];
    budgetControl.setValue('');
    budgetControl.markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('div:has(input[formControlName="budget"]) small'));
    expect(errorMessage.nativeElement.textContent).toContain('Enter a valid budget amount');
  });

  it('should show error if budget is negative', () => {
    const budgetControl = component.grantForm.controls['budget'];
    budgetControl.setValue(-100);
    budgetControl.markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('div:has(input[formControlName="budget"]) small'));
    expect(errorMessage.nativeElement.textContent).toContain('Enter a valid budget amount');
  });

  it('should show error if grantName is not selected', () => {
    const grantControl = component.grantForm.controls['grantName'];
    grantControl.setValue('');
    grantControl.markAsTouched();
    fixture.detectChanges();

    const errorMessage = fixture.debugElement.query(By.css('div:has(select[formControlName="grantName"]) small'));
    expect(errorMessage.nativeElement.textContent).toContain('Please select a grant');
  });

  it('should log form values on valid submission', () => {
    spyOn(console, 'log');
    
    // Set valid values for the form controls
    component.grantForm.patchValue({
      applicantName: 'Test User',
      email: 'test@example.com',
      organizationName: 'TechOrg',
      grantName: '1',
      budget: 1000,
      projectDescription: 'This is a valid project description with enough characters.'
    });

    // Simulate form submission
    component.onSubmit();
    expect(console.log).toHaveBeenCalledWith('Form Submitted!', component.grantForm.value);
  });
});
