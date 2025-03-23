import { TestBed } from '@angular/core/testing';
 
import { ApiService } from './api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from '../../../environments/environment';
 
describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
 
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });
    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });
 
  afterEach(() => {
    httpMock.verify(); // Ensures no unmatched requests remain
  });
 
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
 
  it('should perform a GET request', () => {
    const testData = { id: 1, name: 'Test' };
 
    service.get('/test-endpoint', null).subscribe(data => {
      expect(data).toEqual(testData);
    });
 
    const req = httpMock.expectOne(`${environment.apiurl}/test-endpoint`);
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('Content-Type')).toBe('application/json');
    req.flush(testData);
  });
 
  it('should perform a POST request', () => {
    const postData = { username: 'user', password: 'pass' };
    const responseData = { success: true };
 
    service.post('/login', postData).subscribe(response => {
      expect(response).toEqual(responseData);
    });
 
    const req = httpMock.expectOne(`${environment.apiurl}/login`);
    expect(req.request.method).toBe('POST');
    expect(req.request.headers.get('Content-Type')).toBe('application/json');
    expect(req.request.body).toEqual(postData);
    req.flush(responseData);
  });
});
 