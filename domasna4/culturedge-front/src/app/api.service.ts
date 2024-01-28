import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { IHeritageSite } from './IHeritageSite';

// Injectable service to handle API requests related to heritage sites.
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(
    private http: HttpClient  
  ) { }

  // Method to fetch heritage sites from the API.
  // Returns an observable of an array of IHeritageSite objects.
  getHeritageSites() : Observable<IHeritageSite[]> {
    return this.http.get<IHeritageSite[]>('/api/sites')
  }
}
