package model.dao;

import model.dto.Comment;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

@Stateless
public class CommentDao extends AbstractDao<Comment> {

//    @PersistenceContext(type = PersistenceContextType.EXTENDED , unitName = "AlarmSys")
//    private EntityManager entityManager;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return null;
//    }
}
