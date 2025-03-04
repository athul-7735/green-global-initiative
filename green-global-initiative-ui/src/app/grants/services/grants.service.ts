import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const httpsOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
}

@Injectable({
  providedIn: 'root'
})
export class GrantsService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8080/api/';

  getGrantApplications(resource: string, options: any = httpsOptions): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/applications');
  }

  getGrantApplicationsById(resource: string, options: any = httpsOptions): Observable<any> {
    const url = `http://localhost:8080/api/applications/${resource}`;
    return this.httpClient.get(url);
  }
 
  postGrantApplications(resource: string, body: any|null, options: any = httpsOptions): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/applications',body,options);
  }

  updateGrantApplications(resource: string, body: any|null, options: any=httpsOptions): Observable<any> {
    return this.httpClient.patch('http://localhost:8080/api/applications',body,options);
  }
 
  getGrants(resource?: string, options: any = httpsOptions): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/grants');
  }
}
