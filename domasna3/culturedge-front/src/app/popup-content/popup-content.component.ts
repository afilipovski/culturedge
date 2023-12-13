import { HttpClient } from '@angular/common/http';
import { AfterContentInit, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-popup-content',
  templateUrl: './popup-content.component.html',
  styleUrls: ['./popup-content.component.css']
})
export class PopupContentComponent implements AfterContentInit {
  constructor(
    private http: HttpClient
  ) {}
    
  baseUrl = 'http://localhost:8080'

  placeName = ""
  pictureUrl = ""
  description = ""

  ngAfterContentInit(): void {
    if (!this.placeName)
      return;
    this.refreshPhoto();
    this.http.get(`${this.baseUrl}/description?name=${this.placeName}`,{responseType: 'text'}).subscribe(k => {
      this.description = k;
      console.log(this.description);
            
    });
  }

  selectedFile! : File;
  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
  }

  onSubmit() {
    if (this.selectedFile) {
      const photoUploadData = new FormData();
      photoUploadData.append('file',this.selectedFile,this.selectedFile.name);
      photoUploadData.append('name',this.placeName);
      this.http.post(`${this.baseUrl}/photo`,photoUploadData,{responseType: 'text'}).subscribe(k => {
        this.refreshPhoto();
      })
    }
    if (this.description) {
      const descriptionData = new FormData();
      descriptionData.append('name',this.placeName);
      descriptionData.append('desc',this.description)
      this.http.post(`${this.baseUrl}/description`,descriptionData,{responseType: 'text'}).subscribe(k => {
      });
    }
  }

  refreshPhoto() {
    this.pictureUrl = `${this.baseUrl}/photo?name=${this.placeName}&lastmod=${Date.now()}`
  }
}
