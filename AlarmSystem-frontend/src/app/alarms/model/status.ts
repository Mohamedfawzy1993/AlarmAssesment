import {Alarm} from './alarm';

export class Status {

  id: number;
  status: string;
  statusChangeTimestamp: Date;
  alarmByAlarmId: Alarm;
}
