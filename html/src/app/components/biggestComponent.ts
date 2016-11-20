import {PathService} from "../services/path.service";
import {BiggestService} from "../services/biggest.service";

export abstract class BiggestComponent {

  constructor( protected pathService: PathService, protected biggestService :BiggestService) {
    this.getBiggest();
  }
    openPath(path?: string) {
    this.pathService.openPath(path);

  }

  deletePath(path?: string) {
    this.pathService.openPath(path);
  }

  abstract getBiggest() :void;
}

