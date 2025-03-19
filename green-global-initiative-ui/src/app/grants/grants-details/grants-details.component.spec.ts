import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrantsDetailsComponent } from './grants-details.component';

describe('GrantsDetailsComponent', () => {
  let component: GrantsDetailsComponent;
  let fixture: ComponentFixture<GrantsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrantsDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrantsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
