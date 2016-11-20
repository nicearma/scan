import { Pipe, PipeTransform } from '@angular/core';


@Pipe({name: 'mega'})
export class Mega implements PipeTransform {
  transform(value: number): string {

    if(value<1000){
      return this.toString(value) +" [byte]";
    }
    var k=value/1000;
    if(k<1000){
      return this.toString(k) +" [KB]";
    }
    k=k/1000;
    if(k<1000){
      return this.toString(k) +" [MB]";
    }
    k=k/1000;
    if(k<1000){
      return this.toString(k) +" [GB]";
    }
    k=k/1000;
    return this.toString(k) +" [TB]";

  }
  toString(k:number){
   return k.toString().substr(0,5);
  }
}
