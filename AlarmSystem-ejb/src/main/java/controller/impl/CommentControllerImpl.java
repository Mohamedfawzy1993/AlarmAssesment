package controller.impl;

import model.dao.AlarmDao;
import model.dao.CommentDao;
import model.entities.Alarm;
import model.entities.Comment;
import model.entities.Pagination;
import model.entities.ResultSet;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@LocalBean
public class CommentControllerImpl {


    @Inject
    private CommentDao commentDao;
    @Inject private AlarmDao alarmDao;
    public ResultSet<Comment> findAlarmComments(long alarmID, Pagination pagination) {
        return null;
    }

    public void createComment(Comment comment) {
        if (comment.getComment() != null && comment.getComment().length() > 0) {
            comment.setCommentTimestamp(LocalDateTime.now());
            List<Alarm> alarmList = this.alarmDao.getAlarmsByID(comment.getAlarmID() , null);
            if(alarmList.size() > 0){
                if(comment.getAlarmByAlarmId() == null)
                    comment.setAlarmByAlarmId(alarmList.get(0));
//                comment.getAlarmByAlarmId().setAlarmId(comment.getAlarmID());
                this.commentDao.insert(comment);
            }


        }
    }
}
