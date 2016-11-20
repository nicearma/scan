import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {BiggestComponent} from "../biggestComponent";
import {PathService} from "../../services/path.service";
import {BiggestExtension} from "../../interface/BiggestExtension";

@Component({
  selector: 'biggest-extension',
  templateUrl: 'biggest-extension.component.html',
  styleUrls: ['biggest-extension.component.scss'],
  providers: [PathService,BiggestService]
})
export class BiggestExtensionComponent extends BiggestComponent{

  extensions:Array<BiggestExtension>;

  constructor(protected pathService: PathService, protected biggestService: BiggestService) {
    super(pathService, biggestService);
  }

  getBiggest() {
    let that = this;
    this.biggestService.getBiggestExtension().subscribe(result => {
      that.extensions = result;
    })
  }
}
