import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {Biggest} from "../biggest";
import {PathService} from "../../services/path.service";
import {ScanService} from "../../services/scan.service";
import {Folder} from "../../interface/Folder";
import {EventEmitters} from "../../services/eventEmitter.service";
import {BiggestFolder} from "../../interface/BiggestFolder";

@Component({
  selector: 'biggest-folder',
  templateUrl: 'biggest-folder.component.html',
  styleUrls: ['biggest-folder.component.scss'],
  providers: [PathService,ScanService]
})
export class BiggestFolderComponent  extends Biggest {

  folders: Array<BiggestFolder> = [];

  constructor(private scanService: ScanService, protected pathService: PathService){
    super(pathService);
    let that=this;

  }
}
