package controller.impl;

import controller.interfaces.CommentController;
import model.dto.Comment;
import model.dto.Pagination;
import model.dto.ResultSet;

import javax.ejb.Local;

@Local(CommentController.class)
public class CommentControllerImpl implements CommentController {

    public ResultSet<Comment> findAlarmComments(long alarmID, Pagination pagination) {
        return null;
    }
}
