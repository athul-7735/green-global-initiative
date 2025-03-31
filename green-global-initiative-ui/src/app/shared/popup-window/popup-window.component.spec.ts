import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupWindowComponent } from './popup-window.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('PopupWindowComponent', () => {
  let component: PopupWindowComponent;
  let fixture: ComponentFixture<PopupWindowComponent>;
  let dialogRefMock: jasmine.SpyObj<MatDialogRef<PopupWindowComponent>>;

  beforeEach(async () => {
    dialogRefMock = jasmine.createSpyObj('MatDialogRef', ['close']);
    await TestBed.configureTestingModule({
      imports: [PopupWindowComponent],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: { title: 'Test Title', message: 'Test Message' } },
        { provide: MatDialogRef, useValue: dialogRefMock },
      ],
      schemas: [NO_ERRORS_SCHEMA]  
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopupWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Test if dialog data is injected correctly
  it('should receive title and message data from MAT_DIALOG_DATA', () => {
    expect(component.data.title).toBe('Test Title');
    expect(component.data.message).toBe('Test Message');
  });

  // Test if the close method is called on close button click
  it('should close the dialog when close is called', () => {
    component.close();
    expect(dialogRefMock.close).toHaveBeenCalled();
  });
});
