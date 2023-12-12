import { AfterViewInit, Component } from '@angular/core';

@Component({
  selector: 'app-popup-content',
  templateUrl: './popup-content.component.html',
  styleUrls: ['./popup-content.component.css']
})
export class PopupContentComponent implements AfterViewInit {
  ngAfterViewInit(): void {
    if (!this.placeName)
      return;
    console.log(this.placeName);
  }
  placeName = ""
}
