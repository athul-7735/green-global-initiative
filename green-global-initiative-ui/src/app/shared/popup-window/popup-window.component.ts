import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-popup-window',
  imports: [MatDialogModule],
  templateUrl: './popup-window.component.html',
  styleUrl: './popup-window.component.scss'
})
export class PopupWindowComponent {
  
  constructor(private dialogRef: MatDialogRef<PopupWindowComponent>, 
    @Inject(MAT_DIALOG_DATA) public data: { title: string; message: string }) {}

  close() {
    this.dialogRef.close();
  }
}
