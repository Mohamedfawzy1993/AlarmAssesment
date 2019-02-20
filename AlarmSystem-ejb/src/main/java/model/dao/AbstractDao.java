package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
@Transactional
public abstract class AbstractDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    EntityManager getEntityManager(){
        return this.entityManager;
    }

    public void insert(T persistObj) {
        getEntityManager().persist(persistObj);
    }

    public boolean delete(T persistObj) {
        try {
            getEntityManager().remove(persistObj);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public void update(T persistObj) {
        getEntityManager().merge(persistObj);
    }

    public T getByID(String id) {

        return getEntityManager().find(type , id);
    }

}
