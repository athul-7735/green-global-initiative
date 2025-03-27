import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
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
 
  private apiUrl = environment.apiurl;
 
  getGrantApplications(resource: string, options: any = httpsOptions): Observable<any> {
    return this.httpClient.get(this.apiUrl+'applications');
  }

  getGrantApplicationsById(resource: string, options: any = httpsOptions): Observable<any> {
    const url = this.apiUrl+`applications/${resource}`;
    return this.httpClient.get(url);
  }
 
  postGrantApplications(resource: string, body: any|null, options: any = httpsOptions): Observable<any> {
    return this.httpClient.post(this.apiUrl+'applications',body,options);
  }

  updateGrantApplications(resource: string, body: any|null, options: any=httpsOptions): Observable<any> {
    return this.httpClient.patch(this.apiUrl+'applications',body,options);
  }
 
  getGrants(resource?: string, options: any = httpsOptions): Observable<any> {
    return this.httpClient.get(this.apiUrl+'grants');
  }
}
