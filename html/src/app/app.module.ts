import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { MaterialModule } from '@angular/material';
import '@angular/material/core/theming/prebuilt/deeppurple-amber.css';
import { AppComponent } from './app.component';
import {Mega} from "./pipes/mega";

@NgModule({
  declarations: [
    AppComponent,
    Mega
  ],
  imports: [
    MaterialModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
