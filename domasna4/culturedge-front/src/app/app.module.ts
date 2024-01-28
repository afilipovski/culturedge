import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AboutComponent } from './about/about.component';
import { MapComponent } from './map/map.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PopupContentComponent } from './popup-content/popup-content.component';
import { FaqComponent } from './faq/faq.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { PrivacyandtermsComponent } from './privacyandterms/privacyandterms.component';
import { FooterComponent } from './footer/footer.component';
import { LocationService } from './services/geolocation.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AboutComponent,
    MapComponent,
    PopupContentComponent,
    FaqComponent,
    FeedbackComponent,
      PrivacyandtermsComponent,
      FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [LocationService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
