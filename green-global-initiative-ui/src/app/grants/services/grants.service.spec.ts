import { TestBed } from '@angular/core/testing';

import { GrantsService } from './grants.service';
import { HttpClientModule } from '@angular/common/http';

describe('GrantsService', () => {
  let service: GrantsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
    });
    service = TestBed.inject(GrantsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
