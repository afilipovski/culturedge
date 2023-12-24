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

  baseUrl = '/feedback';
  name = "";
  email = "";
  message = "";
  editMode = false;

  selectedFile!: File;

  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
  }

  onSubmit() {
    this.postName();
    this.postEmail();
    this.postMessage()
    this.name = "";
    this.email = "";
    this.message = "";
    console.log('Form fields cleared:', this.name, this.email, this.message);
    this.editMode = false;
  }

  postMessage() {
    if (this.message) {
      const messageData = new FormData();
      messageData.append('message', this.message)
      this.http.post(`${this.baseUrl}/message`,messageData, {responseType: 'text'}).subscribe(k => {
      });
    }
  }
  postName() {
    if (this.name) {
      const nameData = new FormData();
      nameData.append('name', this.name)
      this.http.post(`${this.baseUrl}/name`,nameData, {responseType: 'text'}).subscribe(k => {
      });
    }
  }
  postEmail() {
    if (this.email) {
      const emailData = new FormData();
      emailData.append('email', this.email)
      this.http.post(`${this.baseUrl}/email`,emailData, {responseType: 'text'}).subscribe(k => {
      });
    }
  }


  @ViewChild('#file-upload', {read: ViewContainerRef}) fileUpload!: ViewContainerRef;

}