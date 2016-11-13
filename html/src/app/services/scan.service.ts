import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable, BehaviorSubject} from 'rxjs/Rx';
import {EventBusResult} from "../interface/EventBusResult";
import {Dir} from "../interface/Dir";
import {File} from "../interface/File";
import {GeneralService} from "./general.service";

declare var EventBus:any;

@Injectable()
export class ScanService extends GeneralService{

  private _dirs: BehaviorSubject<Dir> = new BehaviorSubject({path:undefined});
  public dirs: Observable<Object> = this._dirs.asObservable();
  private _files: BehaviorSubject<File> = new BehaviorSubject({PATH:undefined, NAME:undefined, SIZE:undefined});
  public files: Observable<File> = this._files.asObservable();
  eb:any;

  private api='/api/socket';
  private scanUrl='/api/scan';
  private beginUrl='/api/files';

  constructor(protected http: Http) {
    super(http);
    this.eb= new EventBus(this.server+this.api);
    var eb =this.eb;
    let that=this;

    eb.onopen = function() {

        // set a handler to receive a message
        eb.registerHandler('eventHttpFileProps', function (error, message) {
           that._files.next(<File> that.tranform(message));
        });

        // set a handler to receive a message
        eb.registerHandler('eventHttpDirScaned', function (error, message) {
          that._dirs.next(<Dir> that.tranform(message));
        });

    }
  }

  getObservableFile() {
    return this.files;
  }
  getObservableDirs() {
    return this.dirs;
  }

  scan(path :String) : void  {
    var jsonPath={path};
    this.http.post(this.server+this.scanUrl,jsonPath).subscribe(e=>console.log(e));
  }

  getBiggestFiles() : Observable<Array<File>>  {

    return this.http.post(this.server+this.beginUrl, null).map(this.extractData);
  }


  tranform(message :EventBusResult) : Object  {
    let result : EventBusResult=message;
    if(!result && !result.body){
      return {};
    }
    return result.body;
  }


}
