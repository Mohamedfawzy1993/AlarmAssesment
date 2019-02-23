import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConfigParams} from '../shared/config/config-params';

@Injectable({
  providedIn: 'root'
})
export class GeneratorService {


  constructor(private http: HttpClient) {
  }


  public findSchedulerStatus() {
    const servicePath = 'status';
    return this.http.get(ConfigParams.GENERATOR_URL + servicePath);
  }

  public startScheduler(interval: number) {
    const servicePath = 'start';
    let httpParam = new HttpParams();
    httpParam = httpParam.append('interval', interval + '');
    return this.http.post(ConfigParams.GENERATOR_URL + servicePath, null, {params: httpParam});
  }

  public stopScheduler() {
    const servicePath = 'stop';
    return this.http.post(ConfigParams.GENERATOR_URL + servicePath , null);
  }

}
