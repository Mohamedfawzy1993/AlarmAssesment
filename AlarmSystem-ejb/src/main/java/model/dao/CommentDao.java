package model.dao;

import model.dao.AbstractDao;
import model.entities.Comment;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@Transactional
public class CommentDao extends AbstractDao<Comment> {

//    @PersistenceContext(type = PersistenceContextType.EXTENDED , unitName = "AlarmSys")
//    private EntityManager entityManager;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return null;
//    }

    public List<Comment> getCommentsByAlarmID(String alarmID) {
        Query query = getEntityManager()
                .createQuery("select c from Comment c where c.alarmByAlarmId.alarmId = :alarmID");
        query.setParameter("alarmID", alarmID);
        List<Comment> comments = query.getResultList();
        return comments;
    }
}
