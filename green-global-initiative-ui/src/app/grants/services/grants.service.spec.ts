import { TestBed } from '@angular/core/testing';
import { GrantsService } from './grants.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from '../../../environments/environment';

describe('GrantsService', () => {
  let service: GrantsService;
  let httpMock: HttpTestingController;
  let apiUrl = environment.apiurl;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GrantsService],
    });
    service = TestBed.inject(GrantsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Ensure no outstanding HTTP requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch grant applications', () => {
    const mockResponse = [{ id: 1, name: 'Grant A' }, { id: 2, name: 'Grant B' }];
    
    service.getGrantApplications('').subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(apiUrl + 'applications');
    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

  it('should fetch grant application by ID', () => {
    const mockResponse = { id: 1, name: 'Grant A' };
    
    service.getGrantApplicationsById('1').subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(apiUrl + 'applications/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

  it('should post a new grant application', () => {
    const mockBody = { name: 'New Grant' };
    const mockResponse = { success: true };
    
    service.postGrantApplications('', mockBody).subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(apiUrl + 'applications');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockBody);
    req.flush(mockResponse);
  });

  it('should update a grant application', () => {
    const mockBody = { id: 1, name: 'Updated Grant' };
    const mockResponse = { success: true };
    
    service.updateGrantApplications('', mockBody).subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(apiUrl + 'applications');
    expect(req.request.method).toBe('PATCH');
    expect(req.request.body).toEqual(mockBody);
    req.flush(mockResponse);
  });

  it('should fetch grants', () => {
    const mockResponse = [{ id: 1, title: 'Grant A' }, { id: 2, title: 'Grant B' }];
    
    service.getGrants().subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(apiUrl + 'grants');
    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });
});
