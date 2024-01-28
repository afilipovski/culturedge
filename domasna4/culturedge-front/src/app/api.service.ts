import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { IHeritageSite } from './IHeritageSite';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(
    private http: HttpClient  
  ) { }

  getHeritageSites() : Observable<IHeritageSite[]> {
    return this.http.get<IHeritageSite[]>('/api/sites')
  }
}
