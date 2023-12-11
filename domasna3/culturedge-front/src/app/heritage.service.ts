import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'
import { IHeritageSite } from './IHeritageSite';

@Injectable({
  providedIn: 'root'
})
export class HeritageService {
  constructor(
    private http: HttpClient  
  ) { }

  getHeritageSites() : Observable<IHeritageSite[]> {
    return this.http.get<IHeritageSite[]>('http://localhost:8080/sites')
  }
}
