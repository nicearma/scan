import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {BiggestComponent} from "../biggestComponent";
import {PathService} from "../../services/path.service";
import {EventEmitters} from "../../services/eventEmitter.service";
import {BiggestFile} from "../../interface/BiggestFile";

@Component({
  selector: 'biggest-file',
  templateUrl: 'biggest-file.component.html',
  styleUrls: ['biggest-file.component.scss'],
  providers: [PathService, BiggestService]
})
export class BiggestFileComponent extends BiggestComponent {

  files: Array<BiggestFile> = [];

  constructor(protected pathService: PathService, protected biggestService: BiggestService) {
    super(pathService, biggestService);
  }

  getBiggest() {
    let that = this;
    this.biggestService.getBiggestFile().subscribe(result => {
      that.files = result;
    })
  }

}
