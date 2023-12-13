import { HttpClient } from '@angular/common/http';
import { AfterContentInit, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-popup-content',
  templateUrl: './popup-content.component.html',
  styleUrls: ['./popup-content.component.css']
})
export class PopupContentComponent implements OnInit, AfterContentInit {
  constructor(
    private http: HttpClient
  ) {}
    
  baseUrl = 'http://localhost:8080/photo'

  ngOnInit(): void {
    
  }

  placeName = ""
  pictureUrl = ""
  description = ""

  ngAfterContentInit(): void {
    if (!this.placeName)
      return;
    this.refreshPhoto();
  }

  selectedFile! : File;
  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
  }

  onSubmit() {
    console.log(this.selectedFile);
    
    const photoUploadData = new FormData();
    photoUploadData.append('file',this.selectedFile,this.selectedFile.name);
    photoUploadData.append('name',this.placeName);

    this.http.post(this.baseUrl,photoUploadData,{responseType: 'text'}).subscribe(k => {
      this.refreshPhoto();
    })
  }

  refreshPhoto() {
    this.pictureUrl = `${this.baseUrl}?name=${this.placeName}&lastmod=${Date.now()}`
  }
}
