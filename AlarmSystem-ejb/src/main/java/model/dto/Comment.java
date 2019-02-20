package model.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Comment {
    private Long id;
    private String comment;
    private Timestamp commentTimestamp;
    private Alarm alarmByAlarmId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = 150)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "commentTimestamp", nullable = false)
    public Timestamp getCommentTimestamp() {
        return commentTimestamp;
    }

    public void setCommentTimestamp(Timestamp commentTimestamp) {
        this.commentTimestamp = commentTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (id != null ? !id.equals(comment1.id) : comment1.id != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (commentTimestamp != null ? !commentTimestamp.equals(comment1.commentTimestamp) : comment1.commentTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (commentTimestamp != null ? commentTimestamp.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Alarm_id", referencedColumnName = "id", nullable = false)
    public Alarm getAlarmByAlarmId() {
        return alarmByAlarmId;
    }

    public void setAlarmByAlarmId(Alarm alarmByAlarmId) {
        this.alarmByAlarmId = alarmByAlarmId;
    }
}
