import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrantApplicationComponent } from './grant-application.component';

describe('GrantApplicationComponent', () => {
  let component: GrantApplicationComponent;
  let fixture: ComponentFixture<GrantApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrantApplicationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrantApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
