import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {ConfigParams} from '../shared/config/config-params';
import {AlarmService} from './service/alarm.service';
import {Alarm} from './model/alarm';
import {ResultSet} from './model/result-set';
import {Util} from '../shared/util';
import {Pagination} from './model/pagination';
import {Params, Router} from '@angular/router';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css'],
  providers: [AlarmService]
})
export class AlarmsComponent implements OnInit {

  constructor(private alarmService: AlarmService, private router: Router) {
  }

  title = 'Alarms';

  webSocket: WebSocket;
  isLoaded = false;
  isFetchingRunning = false;
  alarmsList: Alarm[] = [];

  criteria: Alarm = new Alarm();
  pagination: Pagination = new Pagination();
  activeStatusTypes = {
    activeOnly: 'a',
    activeAndInActive: 'all',
    inactiveOnly: 'i'
  };
  activeStatus = this.activeStatusTypes.activeAndInActive;

  commentsAlarmInput = null;

  @ViewChild('openModal') openModal: ElementRef;
  ngOnInit() {
    document.getElementById('bread').innerText = this.title;
    document.getElementById('title').innerText = this.title;

    this.pagination.pageSize = 500;
    this.pagination.currentPage = 1;

    this.initWebSocket(); // Initial Websocket Connection and Action

    const that = this;

    window.addEventListener('scroll', e => {
      this.onScroll(e);
    });

    this.fetchData(); // Fetch Data on Application Beginning

  }

  onScroll(e) {

    if (this.isFetchingRunning) {
      return;
    }
    const scrollValue = e.target.scrollingElement.scrollTop;
    const maxValue = document.scrollingElement.scrollHeight - document.documentElement.clientHeight;

    if (scrollValue >= maxValue) {
      console.log('Reached the End');
      this.pagination.currentPage += 1;
      this.fetchData();
    }
  }

  isValidDate(date): boolean {
    return isNaN(date);
  }


  // Fetch Data from backend
  fetchData() {
    this.isFetchingRunning = true;
    this.alarmService.getAllAlarms(this.pagination, this.criteria).subscribe(
      (success: ResultSet<Alarm>) => {
        for (const alarm of success.data) {
          // alarm.eventTime = new Date(alarm.eventTime);
          alarm.eventTime = Util.dateStringToDateObject(alarm.eventTime);
          alarm.ceaseTime = Util.dateStringToDateObject(alarm.ceaseTime);
        }
        this.alarmsList.push(...success.data);
        console.log(success.data);
        this.isLoaded = true;
        this.isFetchingRunning = false;
      }
    );
  }

  // Return Severity Color
  severityColor(severity: string) {
    severity = severity.toLowerCase();
    if (severity.toLowerCase() == ConfigParams.WARNING) {
      return 'orange';
    }
    if (severity.toLowerCase() == ConfigParams.MINOR) {
      return 'gray';
    }
    if (severity.toLowerCase() == ConfigParams.MAJOR) {
      return 'yellow';
    }
    if (severity.toLowerCase() == ConfigParams.CRITICAL) {
      return 'red';
    }
  }

  // Initial Websocket Connection With Action
  initWebSocket() {
    this.webSocket = new WebSocket(ConfigParams.WEBSOCKET_URL + 'AlarmSys/alarmNotify');
    this.webSocket.onopen = ev => console.log(ev);
    this.webSocket.onclose = ev => console.log(ev);
    this.webSocket.onmessage = message => {
      const data = JSON.parse(message.data);
      this.handleWebSocketData(data);
    };
  }

  // handle logic of Websocket Received Data
  handleWebSocketData(alarmObj: Alarm) {
    for (let step = 0; step < this.alarmsList.length; step++) {

      // Check if the incoming Alarm Already Exist in AlarmList
      if (alarmObj.alarmId == this.alarmsList[step].alarmId) {
        console.log('found');
        console.log(this.alarmsList[step].alarmId);
        console.log(alarmObj.alarmId);

        this.alarmsList.splice(step, 1);
        alarmObj.eventTime = Util.dateStringToDateObject(alarmObj.eventTime);
        alarmObj.ceaseTime = Util.dateStringToDateObject(alarmObj.ceaseTime);
        this.alarmsList.unshift(alarmObj);
        return;
      }
    }
    this.alarmsList.unshift(alarmObj);
  }

  search() {
    const queryParams: Params = [];

    queryParams.alarmID = this.criteria.alarmId == null ? '' : this.criteria.alarmId;
    queryParams.severity = this.criteria.severity == null ? '' : this.criteria.severity;
    queryParams.description = this.criteria.description == null ? '' : this.criteria.description;
    queryParams.siteID = this.criteria.siteId == null ? '' : this.criteria.siteId;
    this.router.navigate([window.location.pathname], {queryParams});
    this.pagination.currentPage = 1;
    this.fetchData();

  }

  loadComments(alarm: Alarm) {
    this.commentsAlarmInput = alarm;
    this.openModal.nativeElement.click();
  }
}
