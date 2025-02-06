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

  getGrantApplications(resource: string, body: any|null, options?: any): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/applications');
  }
}
