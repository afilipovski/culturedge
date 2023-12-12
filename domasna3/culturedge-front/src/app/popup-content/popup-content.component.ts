import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component } from '@angular/core';
import { GoogleSearchService } from '../google-search.service';

@Component({
  selector: 'app-popup-content',
  templateUrl: './popup-content.component.html',
  styleUrls: ['./popup-content.component.css']
})
export class PopupContentComponent implements AfterViewInit {
  constructor(
    private googleSearch : GoogleSearchService
  ) {}
  ngAfterViewInit(): void {
    if (!this.placeName)
      return;
    console.log(this.placeName);
    this.googleSearch.getPicture(this.placeName).subscribe(url => this.pictureUrl = url);
  }
  placeName = ""
  pictureUrl = ""
}
