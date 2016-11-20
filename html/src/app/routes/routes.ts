
import {Routes} from "@angular/router";
import {RootComponent} from "../components/root/root.component";
import {BiggestFileComponent} from "../components/file/biggest-file.component";
import {BiggestFolderComponent} from "../components/folder/biggest-folder.component";
import {BiggestExtensionComponent} from "../components/extension/biggest-extension.component";
import {SearchComponent} from "../components/search/search.component";

export const SCAN_ROUTES: Routes = [
  {path: '', component: SearchComponent},
  {path: 'biggest-file', component: BiggestFileComponent},
  {path: 'biggest-folder', component: BiggestFolderComponent},
  {path: 'biggest-extension', component: BiggestExtensionComponent},
  {path: 'expert', component: BiggestExtensionComponent}

];
