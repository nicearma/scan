import { Pipe, PipeTransform } from '@angular/core';


@Pipe({name: 'mega'})
export class Mega implements PipeTransform {
  transform(value: number): string {

    if(value<1000){
      return value +" [byte]";
    }
    var k=value/1000;
    if(k<1000){
      return k +" [KB]";
    }
    k=k/1000;
    if(k<1000){
      return k +" [MB]";
    }
    k=k/1000;
    if(k<1000){
      return k +" [GB]";
    }
    k=k/1000;
    return k +" [TB]";

  }
}
