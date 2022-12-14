import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HadarUploadImagesComponent} from './hadar-upload-images/hadar-upload-images.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FileUploadComponent} from './file-upload/file-upload.component';
import {HttpClientModule} from "@angular/common/http";
import { CategoryChartComponent } from './category-chart/category-chart.component';
import { PeopleChartComponent } from './people-chart/people-chart.component';
import { FoodDrinkChartComponent } from './food-drink-chart/food-drink-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    HadarUploadImagesComponent,
    FileUploadComponent,
    CategoryChartComponent,
    PeopleChartComponent,
    FoodDrinkChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
