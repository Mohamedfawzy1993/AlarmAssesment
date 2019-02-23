import {Alarm} from './alarm';

export class Comment {

  id: number;
  comment: string;
  commentTimestamp: Date;
  alarmByAlarmId: Alarm;
  alarmID: number;
}

