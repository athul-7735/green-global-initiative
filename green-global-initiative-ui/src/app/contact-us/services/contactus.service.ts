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
export class ContactusService {

  constructor(private http: HttpClient) { }
    private apiUrl = environment.apiurl;

    postQuery(resource: string, body: any|null, options?: any): Observable<any> {
      return this.http.post(this.apiUrl + resource, body, options? options : httpsOptions);
    }
}
