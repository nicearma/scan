import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {Biggest} from "../biggest";
import {PathService} from "../../services/path.service";
import {EventEmitters} from "../../services/eventEmitter.service";
import {BiggestFile} from "../../interface/BiggestFile";

@Component({
  selector: 'biggest-file',
  templateUrl: 'biggest-file.component.html',
  styleUrls: ['biggest-file.component.scss'],
  providers: [PathService, BiggestService]
})
export class BiggestFileComponent extends Biggest {

  files: Array<BiggestFile> = [];

  constructor(protected pathService: PathService, private informationService: BiggestService) {
    super(pathService);
    let that = this;
    EventEmitters.get("files").subscribe(() => {
      that.getBiggestFile();
    });
  }

  getBiggestFile() {
    this.informationService.getBiggestFile().subscribe(result => {
      console.log(result);
      console.log(this.files);
      this.files = result;
      console.log(this.files);
    })
  }

}
