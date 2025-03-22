import { TestBed } from '@angular/core/testing';

import { ContactusService } from './contactus.service';
import { HttpClientModule } from '@angular/common/http';

describe('ContactusService', () => {
  let service: ContactusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [ContactusService]
    });
    service = TestBed.inject(ContactusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
