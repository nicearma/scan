import {GeneralService} from "./general.service";
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

@Injectable()
export class PathService extends GeneralService{

  private pathUrl="/path";
  private pathOpenUrl=this.pathUrl+"/open";

  constructor(protected http: Http){
    super(http);
  }

  openPath(path :String) : void  {
    var jsonPath={path};
    this.http.post(this.server+this.pathOpenUrl,jsonPath).subscribe(e=>console.log(e));
  }
}
