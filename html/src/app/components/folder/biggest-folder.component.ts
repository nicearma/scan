import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {BiggestComponent} from "../biggestComponent";
import {PathService} from "../../services/path.service";
import {ScanService} from "../../services/scan.service";
import {Folder} from "../../interface/Folder";
import {EventEmitters} from "../../services/eventEmitter.service";
import {BiggestFolder} from "../../interface/BiggestFolder";

@Component({
  selector: 'biggest-folder',
  templateUrl: 'biggest-folder.component.html',
  styleUrls: ['biggest-folder.component.scss'],
  providers: [PathService,BiggestService]
})
export class BiggestFolderComponent  extends BiggestComponent {

  folders: Array<BiggestFolder> = [];

  constructor(protected pathService: PathService, protected biggestService: BiggestService) {
    super(pathService, biggestService);
  }

  getBiggest() {
    let that = this;
    this.biggestService.getBiggestFolder().subscribe(result => {
      that.folders = result;
    })
  }
}
