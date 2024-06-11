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
  cityName: string = "";
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
    // Retrieve the description of the place from the server
    this.http.get(`${this.baseUrl}/description?name=${this.placeName}`,{responseType: 'text'}).subscribe(k => {
      this.description = k;
    });
  }

  selectedFile! : File; // Variable to store the selected file for photo upload

  // Event handler for file input change event
  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
    this.postPhoto(); // Upload the selected photo
  }

  // Method to handle form submission
  onSubmit() {
    this.postPhoto();
    this.postDescription();
    this.editMode = false;
  }

  // Method to upload photo
  postPhoto() {
    if (this.selectedFile) {
      const photoUploadData = new FormData(); // Create FormData object for photo upload
      photoUploadData.append('file',this.selectedFile,this.selectedFile.name);
      photoUploadData.append('name',this.placeName);
      // Post the photo to the server
      this.http.post(`${this.baseUrl}/photo`,photoUploadData,{responseType: 'text'}).subscribe(k => {
        this.refreshPhoto();
      })
    }
  }
  // Method to post description
  postDescription() {
    if (this.description) {
      const descriptionData = new FormData(); // Create FormData object for description
      descriptionData.append('name',this.placeName);
      descriptionData.append('desc',this.description)
      // Post the description to the server
      this.http.post(`${this.baseUrl}/description`,descriptionData,{responseType: 'text'}).subscribe(k => {
      });
    }
  }

  // Method to refresh the photo
  refreshPhoto() {
    this.pictureUrl = `${this.baseUrl}/photo?name=${this.placeName}&lastmod=${Date.now()}` // Construct photo URL with cache busting
  }

  @ViewChild('#file-upload', { read: ViewContainerRef }) fileUpload!: ViewContainerRef;

  // Method to prompt file upload
  promptUploadPhoto() {
    this.fileUpload.get(0)
  }
}
