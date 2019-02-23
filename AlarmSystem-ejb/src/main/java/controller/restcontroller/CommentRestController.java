package controller.restcontroller;

import controller.impl.CommentControllerImpl;
import model.entities.Comment;
import model.entities.ResultSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Stateless
@Path("comment")
public class CommentRestController {

    @Inject
    private CommentControllerImpl commentController;

    @POST
    public Comment addComment(Comment comment){
        this.commentController.createComment(comment);
        return comment;
    }

    @GET
    @Path("all")
    public ResultSet<Comment> getCommentListByAlarmID(@QueryParam("alarmID") String alarmID){
        return this.commentController.findAlarmComments(Long.parseLong(alarmID), null);
    }
}
