import { HttpClient } from '@angular/common/http';
import { AfterContentInit, Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-popup-content',
  templateUrl: './popup-content.component.html',
  styleUrls: ['./popup-content.component.css']
})
export class PopupContentComponent implements AfterContentInit {
  constructor(
    private http: HttpClient
  ) {}
    
  baseUrl = '/api';

  placeName = "";
  lat !: number;
  lon !: number;
  pictureUrl = "";
  description = "";
  editMode = false;

  ngAfterContentInit(): void {
    if (!this.placeName)
      return;
    this.refreshPhoto();
    this.http.get(`${this.baseUrl}/description?name=${this.placeName}`,{responseType: 'text'}).subscribe(k => {
      this.description = k;
    });
  }

  selectedFile! : File;
  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
    this.postPhoto();
  }

  onSubmit() {
    this.postPhoto();
    this.postDescription();
    this.editMode = false;
  }

  postPhoto() {
    if (this.selectedFile) {
      const photoUploadData = new FormData();
      photoUploadData.append('file',this.selectedFile,this.selectedFile.name);
      photoUploadData.append('name',this.placeName);
      this.http.post(`${this.baseUrl}/photo`,photoUploadData,{responseType: 'text'}).subscribe(k => {
        this.refreshPhoto();
      })
    }
  }
  postDescription() {
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

  @ViewChild('#file-upload', { read: ViewContainerRef }) fileUpload!: ViewContainerRef;
  
  promptUploadPhoto() {
    this.fileUpload.get(0)
  }
}
