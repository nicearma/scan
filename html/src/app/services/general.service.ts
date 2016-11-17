
import { Http, Response } from '@angular/http';
import {Observable, BehaviorSubject} from 'rxjs/Rx';


export class GeneralService{

  protected server='http://localhost:8080';

  constructor(protected http: Http){}

  protected extractData(res: Response) {
    let body = res.json();
    return body || { };
  }


}
