import {PathService} from "../services/path.service";

export class Biggest {

  constructor( protected pathService: PathService) {
  }
    openPath(path?: string) {
    this.pathService.openPath(path);
  }

  deletePath(path?: string) {
    this.pathService.openPath(path);
  }
}

