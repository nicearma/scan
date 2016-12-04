import {Component} from "@angular/core";
import {ScanService} from "../../services/scan.service";
import {Folder} from "../../interface/Folder";
import {EventEmitters} from "../../services/eventEmitter.service";

@Component({
  selector: 'folder-scaned',
  templateUrl: 'folder-scaned.component.html',
  styleUrls: ['folder-scaned.component.scss'],
  providers: [ScanService]
})
export class FolderScanedComponent {

  size = 20;
  folders: Folder[] = [];
  foldersScaned: Folder[] = [];

  constructor(private scanService: ScanService) {

    let that = this;
    EventEmitters.get("search").subscribe(() => {
      that.folders = [];
    });

    this.scanService.getObservableDirs().subscribe((folder: Folder) => {
      if (folder && folder.path) {
        that.foldersScaned.push(folder);
        if(that.size>that.foldersScaned.length){
          that.addFolders();
        }

      }
    });
  }

  addFolders() {

    console.log("addFolders");
    let maxSize = this.foldersScaned.length;
    let shotSize = this.folders.length;
    if (maxSize > shotSize) {
      var maxSizeAdd=maxSize;
      if(shotSize+maxSize<maxSize){
         maxSizeAdd+=maxSize;
      }
      this.folders=  this.folders.concat(this.foldersScaned.slice(shotSize,maxSizeAdd))
    }


  }
}
