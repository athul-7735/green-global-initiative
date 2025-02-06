import { TestBed } from '@angular/core/testing';

import { GrantsService } from './grants.service';

describe('GrantsService', () => {
  let service: GrantsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GrantsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
