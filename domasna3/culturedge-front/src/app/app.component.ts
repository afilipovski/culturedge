import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'culturedge-front';
  constructor(private router: Router) {}

  shouldShowNavbar(): boolean {
    return this.router.url !== '/map';
  }
}
