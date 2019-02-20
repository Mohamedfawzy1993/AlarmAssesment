package controller.interfaces;

import model.dto.Comment;
import model.dto.Pagination;
import model.dto.ResultSet;

public interface CommentController {

    ResultSet<Comment> findAlarmComments(long alarmID , Pagination pagination); // Find Comments Related to Specific Alarm Paginated

}
