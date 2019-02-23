import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConfigParams} from '../../shared/config/config-params';
import {Pagination} from '../model/pagination';
import {Alarm} from '../model/alarm';

@Injectable({
  providedIn: 'root'
})
export class AlarmService {

  constructor(private http: HttpClient) {
  }


  getAllAlarms(pagination: Pagination, criteria: Alarm) {
    let params = new HttpParams();


    if (criteria != null) {
      if (criteria.alarmId != null) {
        params = params.append('id', criteria.alarmId.toString());
      }
      if (criteria.siteId != null) {
        params = params.append('site', criteria.siteId.toString());
      }
      if (criteria.severity != null) {
        params = params.append('severity', criteria.severity.toString());
      }
      if (criteria.description != null) {
        params = params.append('description', criteria.description.toString());
      }
      if (criteria.isActive != null) {
        params = params.append('isActive', criteria.isActive.toString());
      }
    }
    if (pagination != null) {
      // Pagination Current Page Value
      if (pagination.currentPage != null) {
        params = params.append('currentPage', pagination.currentPage.toString());
      } else {
        params = params.append('currentPage', '1');
      }
      // Pagination pageSize Value
      if (pagination.pageSize != null) {
        params = params.append('pageSize', pagination.pageSize.toString());
      } else {
        params = params.append('pageSize', '50');
      }
    } else {
      params = params.append('currentPage', '1');
      params = params.append('pageSize', ConfigParams.PAGINATION_PAGE_SIZE_DEFAULT_VALUE.toString());

    }


    return this.http.get(ConfigParams.ALARM_URL + 'all', {params: params});
  }
}
