import {Component} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {Biggest} from "../biggest";
import {PathService} from "../../services/path.service";
import {BiggestExtension} from "../../interface/BiggestExtension";

@Component({
  selector: 'biggest-extension',
  templateUrl: 'biggest-extension.component.html',
  styleUrls: ['biggest-extension.component.scss'],
  providers: [PathService]
})
export class BiggestExtensionComponent extends Biggest{

  extensions:Array<BiggestExtension>;
}
