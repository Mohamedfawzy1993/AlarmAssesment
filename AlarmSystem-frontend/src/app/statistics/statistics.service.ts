import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConfigParams} from '../shared/config/config-params';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http: HttpClient) {}



  public findAlarmSeverityStatistics() {
    const servicePath = 'alarmSeverity';
    return this.http.get(ConfigParams.STATISTICS_URL + servicePath);
  }

  public findAlarmActiveAndCeasedStatistics() {
    const servicePath = 'activeAndCeased';
    return this.http.get(ConfigParams.STATISTICS_URL + servicePath);
  }

  public findTopFiveSites() {
    const servicePath = 'topSites';
    return this.http.get(ConfigParams.STATISTICS_URL + servicePath);
  }
}
