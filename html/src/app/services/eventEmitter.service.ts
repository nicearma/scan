import {EventEmitter} from "@angular/core";

export class EventEmitters{

  private static eventEmitters: { [id: string]: EventEmitter<any> } = {};

  static get(id: string): EventEmitter<any> {
    if (!this.eventEmitters[id])
      this.eventEmitters[id] = new EventEmitter();
    return this.eventEmitters[id];
  }
}

