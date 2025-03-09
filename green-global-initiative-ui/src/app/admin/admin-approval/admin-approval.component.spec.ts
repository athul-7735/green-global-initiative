import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminApprovalComponent } from './admin-approval.component';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { ToastrModule, ToastrService } from 'ngx-toastr';

describe('AdminApprovalComponent', () => {
  let component: AdminApprovalComponent;
  let fixture: ComponentFixture<AdminApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminApprovalComponent, HttpClientModule, ToastrModule.forRoot()],
      providers:[
        {
          provide: ActivatedRoute,
          useValue: {
            params: of({ id: '123' }), // ✅ Mock route parameters
            snapshot: { queryParams: {}, data: {} }
          }
        },
        ToastrService
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
