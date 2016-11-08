import { Component } from '@angular/core';
import { Dir } from './interface/Dir';
import { File } from './interface/File';
import { ScanService } from './services/scan.service';
import {PathService} from "./services/path.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ScanService, PathService]
})
export class AppComponent {
  title = 'app works!';
  dirs : Array<Dir>= [];
  files : Array<File>= [];
  constructor(private scanService: ScanService, private pathService: PathService){
    var that=this;
    this.scanService.getObservableDirs().subscribe((dir: Dir)=>{
      if(dir && dir.path){
        that.dirs.push(dir);
      }
    });
    this.scanService.getObservableFile().subscribe((file)=>{
      if(file&& file.NAME){
        file.SIZE=file.SIZE/12500000;
        that.files.push(file);
      }
    });
  }
  search (term: string) {

     this.scanService.scan(term);
  }
  openPath(path: string){
    this.pathService.openPath(path);
  }
  changeTab($event){
    console.log($event);
    if($event.index===1){
      this.scanService.getBiggestFiles().subscribe(result=>{
        this.files=result;
      })
    }

  }

}
