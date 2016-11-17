import {Component, Output, EventEmitter} from "@angular/core";
import {BiggestService} from "../../services/biggest.service";
import {ScanService} from "../../services/scan.service";
import {EventEmitters} from "../../services/eventEmitter.service";

@Component({
  selector: 'search',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.scss'],
  providers: [ScanService]
})
export class SearchComponent {

  constructor(private scanService:ScanService){

  }

  search(term: string) {
    EventEmitters.get("search").emit({});
    this.scanService.scan(term);
  }

}
