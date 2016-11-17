import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {Biggest} from "../biggest";
import {PathService} from "../../services/path.service";
import {ScanService} from "../../services/scan.service";
import {Folder} from "../../interface/Folder";
import {EventEmitters} from "../../services/eventEmitter.service";

@Component({
  selector: 'folder-scaned',
  templateUrl: 'folder-scaned.component.html',
  styleUrls: ['folder-scaned.component.scss'],
  providers: [ScanService]
})
export class FolderScanedComponent  {

  folders: Array<Folder> = [];

  constructor(private scanService: ScanService){

    let that=this;
    EventEmitters.get("search").subscribe(()=>{
      that.folders=[];
    });

    this.scanService.getObservableDirs().subscribe((folder: Folder) => {
      if (folder && folder.path) {
        that.folders.push(folder);
      }
    });
  }
}
