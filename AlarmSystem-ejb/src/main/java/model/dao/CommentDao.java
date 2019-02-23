package model.dao;

import model.entities.Comment;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

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
}
