import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HadarUploadImagesComponent } from './hadar-upload-images/hadar-upload-images.component';
import { HadarStatisticsComponent } from './hadar-statistics/hadar-statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    HadarUploadImagesComponent,
    HadarStatisticsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
