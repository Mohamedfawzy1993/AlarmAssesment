import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedSubjectService {

  constructor() {
  }

  private _dashboardRefreshEvent: EventEmitter<number> = new EventEmitter();


  get dashboardRefreshEvent(): EventEmitter<number> {
    return this._dashboardRefreshEvent;
  }
}
