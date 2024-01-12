import { HttpClient } from '@angular/common/http';
import { AfterContentInit, Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements AfterContentInit {
  constructor(
      private http: HttpClient
  ) {
  }
  ngAfterContentInit(): void {
    console.log('After content initialization');
  }

  baseUrl = '/api';
  name = "";
  email = "";
  message = "";
  editMode = false;

  selectedFile!: File;

  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
  }

  postFeedback() {
    if (this.name && this.email && this.message) {
      const feedbackData = new FormData();
      feedbackData.append('name',this.name);
      feedbackData.append('email',this.email);
      feedbackData.append('message',this.message);
      this.http.post(`${this.baseUrl}/feedback`,feedbackData,{responseType: 'text'}).subscribe(k => {
        this.name = "";
        this.email = "";
        this.message = "";
      });
    }
  }

  @ViewChild('#file-upload', {read: ViewContainerRef}) fileUpload!: ViewContainerRef;

}