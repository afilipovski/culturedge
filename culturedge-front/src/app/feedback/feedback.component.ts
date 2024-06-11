import { HttpClient } from '@angular/common/http';
import { AfterContentInit, Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements AfterContentInit {
  constructor(
      private http: HttpClient // Injecting HttpClient for making HTTP requests
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

  // Function triggered when the file input changes
  onFileChanged(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files![0];
  }

  // Function to post feedback
  postFeedback() {
    if (this.name && this.email && this.message) {
      // Creating FormData object to send feedback data
      const feedbackData = new FormData();
      feedbackData.append('name',this.name);
      feedbackData.append('email',this.email);
      feedbackData.append('message',this.message);
      // Sending POST request to the feedback API endpoint
      this.http.post(`${this.baseUrl}/feedback`,feedbackData,{responseType: 'text'}).subscribe(k => {
        // Resetting input fields after successful feedback submission
        this.name = "";
        this.email = "";
        this.message = "";
      });
    }
  }

  @ViewChild('#file-upload', {read: ViewContainerRef}) fileUpload!: ViewContainerRef; // Reference to file upload input

}