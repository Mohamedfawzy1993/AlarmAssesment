import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConfigParams} from '../../../shared/config/config-params';
import {Comment} from '../../model/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {
  }

  addComment(comment: Comment) {
    return this.http.post(ConfigParams.COMMENT_URL, comment);
  }

  getCommentsByAlarmID(alarmID) {
    let params = new HttpParams();
    params = params.append('alarmID' , alarmID);
    return this.http.get(ConfigParams.COMMENT_URL + 'all' , {params:params});
  }
}
