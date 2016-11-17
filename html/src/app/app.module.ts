import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { MaterialModule } from '@angular/material';
import '@angular/material/core/theming/prebuilt/deeppurple-amber.css';
import {RootComponent} from './components/root/root.component';
import {Mega} from "./pipes/mega";
import {BiggestFileComponent} from "./components/file/biggest-file.component";
import {BiggestFolderComponent} from "./components/folder/biggest-folder.component";
import {BiggestExtensionComponent} from "./components/extension/biggest-extension.component";
import {SearchComponent} from "./components/search/search.component";
import {FolderScanedComponent} from "./components/folder/folder-scaned.component";

@NgModule({
  declarations: [
    RootComponent,
    Mega,
    FolderScanedComponent,
    BiggestExtensionComponent,
    BiggestFileComponent,
    BiggestFolderComponent,
    SearchComponent
  ],
  imports: [
    MaterialModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [RootComponent]
})
export class AppModule { }
