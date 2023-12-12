import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'ol';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GoogleSearchService {

  constructor(
    private http : HttpClient
  ) { }

  getPicture(name:string) {
    return this.http.get(`https://www.googleapis.com/customsearch/v1?key=AIzaSyBt3oBXAU17PEqarTPANqT1Gk7VI3zokPk&cx=52decd65c197b4ac2&q=${name} macedonia&searchType=image&imgType=photo`)
    .pipe(map(k => {
      let newK : any = k;
      return newK.items[0].link
    }));
  }
}
