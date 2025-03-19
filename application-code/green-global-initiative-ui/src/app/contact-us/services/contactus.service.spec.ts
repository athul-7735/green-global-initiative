import { TestBed } from '@angular/core/testing';

import { ContactusService } from './contactus.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';

describe('ContactusService', () => {
  let service: ContactusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [importProvidersFrom(HttpClientModule)]
    });
    service = TestBed.inject(ContactusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
