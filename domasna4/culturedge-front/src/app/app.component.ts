import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocationService } from "./services/geolocation.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'culturedge-front';

  constructor(private router: Router, private locationService: LocationService) {}

  ngOnInit(): void {
    this.getLocation();
  }
  // Method to determine whether to show the navbar based on the current URL
  shouldShowNavbar(): boolean {
    return this.router.url !== '/map';
  }

  // Method to get the current location of the user
  getLocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;
        this.locationService.setUserLocation(latitude, longitude);
      });
    } else {
      console.log('Geolocation is not supported by this browser.');
    }
  }
}
