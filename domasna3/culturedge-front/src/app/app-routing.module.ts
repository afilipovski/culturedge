import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { MapComponent } from './map/map.component';
import {FaqComponent} from "./faq/faq.component";
import {FeedbackComponent} from "./feedback/feedback.component";

const routes: Routes = [
  { path: 'map', component: MapComponent},
  { path: 'faq', component: FaqComponent },
  { path: 'feedback', component: FeedbackComponent },
  { path: '**', component: AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
