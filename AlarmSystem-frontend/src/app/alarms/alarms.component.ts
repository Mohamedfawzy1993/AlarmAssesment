import {Component, HostListener, OnInit} from '@angular/core';
import {ConfigParams} from '../config/config-params';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css']
})
export class AlarmsComponent implements OnInit {

  constructor() {
  }

  webSocket: WebSocket;

  ngOnInit() {

    this.webSocket = new WebSocket(ConfigParams.WEBSOCKET_URL + 'AlarmSys/alarmNotify');
    this.webSocket.onopen = ev => console.log(ev);
    this.webSocket.onclose = ev => console.log(ev);
    this.webSocket.onmessage = message => {
      console.log('New Message');
      console.log(message);
    };
    const that = this;

    window.addEventListener('scroll', e => {
      this.onScroll(e);
    });

  }

  onScroll(e) {

    const scrollValue = e.target.scrollingElement.scrollTop;
    const maxValue = document.scrollingElement.scrollHeight - document.documentElement.clientHeight;

    if (scrollValue >= maxValue) {
      console.log('Reached the End');
    }
  }


}
