import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { AuthService } from './authentication/services/auth.service';

class MockAuthService {
  isTokenExpired = jasmine.createSpy('isTokenExpired').and.returnValue(false);
  getToken = jasmine.createSpy('getToken').and.returnValue('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c');
  login = jasmine.createSpy('login');
  authStatus$ = of(true);
  getUser = jasmine.createSpy('getUser').and.returnValue({});
  isLoggedAsAdmin = jasmine.createSpy('isLoggedAsAdmin').and.returnValue(false);
}

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let authService: AuthService;
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppComponent, ToastrModule.forRoot(), RouterTestingModule, HeaderComponent, FooterComponent],
      providers: [ToastrService,
        { provide: AuthService, useClass: MockAuthService },
        {
          provide: ActivatedRoute,
          useValue: {
            params: of({ id: '123' }),
            snapshot: { queryParams: {} }
          }
        }
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    fixture.detectChanges();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

});
