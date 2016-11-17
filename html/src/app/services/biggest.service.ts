
import {Injectable} from "@angular/core";
import {GeneralService} from "./general.service";
import {Observable} from "rxjs";
import {File} from "../interface/File";
import {Http} from "@angular/http";
import {BiggestFile} from "../interface/BiggestFile";
import {BiggestFolder} from "../interface/BiggestFolder";
import {BiggestExtension} from "../interface/BiggestExtension";

@Injectable()
export class BiggestService extends GeneralService{

  private biggestUrl=this.server+'/api/biggest';
  private biggestFileUrl=this.biggestUrl+'/file';
  private biggestFolderUrl=this.biggestUrl+'/folder';
  private biggestExtensionUrl=this.biggestUrl+'/extension';

  constructor(protected http: Http){
    super(http);

  }

  getBiggestFile() : Observable<Array<BiggestFile>>  {

    return this.http.post(this.biggestFileUrl, null).map(this.extractData);
  }
  getBiggestFolder() : Observable<Array<BiggestFolder>>  {

    return this.http.post(this.biggestFolderUrl, null).map(this.extractData);
  }
  getBiggestExtension() : Observable<Array<BiggestExtension>>  {
    return this.http.post(this.biggestExtensionUrl, null).map(this.extractData);
  }

}
