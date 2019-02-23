import {Comment} from './comment';
import {Status} from './status';

export class Alarm {

  id: number;
  alarmId: number;
  eventTime: Date;
  severity: string;
  description: string;
  ceaseTime: Date;
  siteId: number;
  isActive: number;
  commentsById: Comment[];
  statusesById: Status[];

}
