package model.dao;

import model.entities.Alarm;
import model.entities.Pagination;
import model.entities.ResultSet;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Stateless
@Transactional
public class AlarmDao extends AbstractDao<Alarm> {

    public List<Alarm> getAlarmsByID(String alarmID , Pagination pagination){
        Query query;
        if(pagination != null){
            int firstResult = pagination.getPageSize() * (pagination.getCurrentPage()-1);
            query = this.getEntityManager().createQuery("select u from Alarm u where u.alarmId like :alarmID ")
                    .setFirstResult(firstResult).setMaxResults(pagination.getPageSize());
        } else {
            query = this.getEntityManager().createQuery("select u from Alarm u where u.alarmId like :alarmID ");
        }
        query.setParameter("alarmID" , "%"+alarmID+"%");
        List<Alarm> alarms = query.getResultList();
        return alarms;
    }

    public List<Alarm> getAllAlarmsByCriteria(Alarm alarm ,Pagination pagination){
        Query query = buildCriteriaQuery(alarm , 2);
        if(pagination != null){
            int firstResult = pagination.getPageSize() * (pagination.getCurrentPage()-1);
            query = query.setFirstResult(firstResult).setMaxResults(pagination.getPageSize());
        }

        List<Alarm> alarms = (List<Alarm>) query.getResultList();
        return alarms;
    }

    // Build JPQL Statment from Alarm Object
    // type 1 for count , 2 for data
    private Query buildCriteriaQuery(Alarm alarm , int type){

        String query = "and ";
        String queryPrefix;
        String orderByClause;
        if(type == 1)
            queryPrefix = "select count(u) from Alarm u where 1 = 1 ";
        else
            queryPrefix = "select u from Alarm u where 1 = 1 ";

        if(alarm.getAlarmId() != null)
            query += "u.alarmId like :alarm and ";
        if(alarm.getSiteId() != null)
            query += "u.siteId like :site and ";
        if(alarm.getSeverity() != null)
            query += "u.severity like :severity and ";
        if(alarm.getDescription() != null)
            query += "u.description like :description and ";

       orderByClause = " order by u.recentChangeTimestamp desc";

       if(query.endsWith("and ")){
           query = query.substring(0,query.lastIndexOf("and "));
       }


        Query jpquery = getEntityManager().createQuery(queryPrefix +
                query + orderByClause);

        if(alarm.getAlarmId() != null)
            jpquery.setParameter("alarm", "%"+alarm.getAlarmId()+"%");
        if(alarm.getSiteId() != null)
            jpquery.setParameter("site", "%"+alarm.getSiteId()+"%");
        if(alarm.getSeverity() != null)
            jpquery.setParameter("severity", "%"+alarm.getSeverity()+"%");
        if(alarm.getDescription() != null)
            jpquery.setParameter("description", "%"+alarm.getDescription()+"%");

        return jpquery;
    }

    public List<Alarm> getAllActiveAlarms(){

        Query query = this.getEntityManager().createQuery("select a from Alarm a where a.isActive = 1 and mod(a.id , 2)=0 ");
        return (List<Alarm>) query.getResultList();

    }

    public ResultSet<HashMap<String, Long>> getAlarmsCountBySeverity(){
        Query query = this.getEntityManager().createQuery(
                "select count(a.id), a.severity  from Alarm a where a.isActive =1 group by a.severity");
        List<Object[]> results = query.getResultList();
        HashMap<String , Long> alarmCountHashmap = new HashMap<>();
        for (Object[] result : results) {
            alarmCountHashmap.put((String)result[1] , (Long)result[0]);
        }

        ResultSet<HashMap<String, Long>> resultSet = new ResultSet<>();
        resultSet.addData(alarmCountHashmap);
        return resultSet;
    }

    public ResultSet<HashMap<String, Long>> getAlarmsActiveVsCeased(){
        Query query = this.getEntityManager().createQuery("select count(a.id) , a.isActive from Alarm a group by a.isActive");
        List<Object[]> results = query.getResultList();
        HashMap<String , Long> alarmCountHashmap = new HashMap<>();
        for (Object[] result : results) {
            alarmCountHashmap.put((Integer)result[1] == 1 ? "Active" : "Ceased" , (Long)result[0]);
        }

        ResultSet<HashMap<String, Long>> resultSet = new ResultSet<HashMap<String, Long>>();
        resultSet.addData(alarmCountHashmap);
        return resultSet;
    }

    public ResultSet<HashMap<String, Long>> getTopAlarmSites(){
        Query query = this.getEntityManager().createQuery("select count(a.siteId) , a.siteId from Alarm a group by a.siteId order by count(a.siteId) desc").setMaxResults(5);
        List<Object[]> results = query.getResultList();
        HashMap<String , Long> alarmCountHashmap = new HashMap<>();
        for (Object[] result : results) {
            alarmCountHashmap.put((String)result[1] , (Long)result[0]);
        }

        ResultSet<HashMap<String, Long>> resultSet = new ResultSet<>();
        resultSet.addData(alarmCountHashmap);
        return resultSet;
    }

}
