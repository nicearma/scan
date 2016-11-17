import {Component} from '@angular/core';
import {Folder} from '../../interface/Folder';
import {File} from '../../interface/File';
import {ScanService} from '../../services/scan.service';
import {PathService} from "../../services/path.service";
import {BiggestService} from "../../services/biggest.service";
import {EventEmitters} from "../../services/eventEmitter.service";

@Component({
  selector: 'root',
  templateUrl: 'root.component.html',
  styleUrls: ['root.component.scss'],
  providers: []
})
export class RootComponent {
  title = 'Scan Go!!!!';

  changeTab($event) {
    console.log($event);
    if ($event.index === 1) {
      EventEmitters.get("files").emit({});
    }

  }

}
