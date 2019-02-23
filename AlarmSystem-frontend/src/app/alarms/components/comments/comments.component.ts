import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Alarm} from '../../model/alarm';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {noWhiteSpace} from '../../../shared/validators/no-whitespace-validator';
import {Comment} from '../../model/comment';
import {CommentService} from './comment-service';
import {ResultSet} from '../../model/result-set';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css'],
  providers: [CommentService]
})
export class CommentsComponent implements OnInit, OnChanges {

  constructor(private formBuilder: FormBuilder,
              private commentService: CommentService) {
  }

  @Input() alarmObj: Alarm;
  comment: string = '';

  commentForm = this.formBuilder.group({
    commentString: [null, [noWhiteSpace(), Validators.maxLength(149)]],
  });

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges): void {
    this.fetchData();
  }

  currentDate() {
    return new Date();
  }

  createComment() {
    if (!this.commentForm.get('commentString').invalid) {
      const commentString = this.commentForm.get('commentString').value;
      if (!(commentString.trim().length <= 0)) {
        const comment = new Comment();
        comment.alarmID = this.alarmObj.alarmId;
        comment.comment = commentString;
        this.commentService.addComment(comment).subscribe(
          (success: Comment) => {
            if (this.alarmObj.commentsById == null) {
              this.alarmObj.commentsById = [];
            }
            this.alarmObj.commentsById.unshift(success);
            this.commentForm.reset();
          }
        );
      }
    }
  }

  fetchData() {
    if (this.alarmObj != null && this.alarmObj.alarmId != null) {
      this.commentService.getCommentsByAlarmID(this.alarmObj.alarmId).subscribe(
        (success: ResultSet<Comment>) => {
          if(success != null && success.data != null)
          this.alarmObj.commentsById = success.data;
        }
      );
    }
  }
}
