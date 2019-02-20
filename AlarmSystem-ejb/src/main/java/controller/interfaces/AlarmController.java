package controller.interfaces;

import model.dao.AlarmDao;
import model.dto.Alarm;
import model.dto.Pagination;
import model.dto.ResultSet;

import javax.ejb.Local;

@Local
public interface AlarmController {

    ResultSet<Alarm> searchAlarmByID(String alarmID , Pagination pagination);     // Find Alarms By Alarm ID
    ResultSet<Alarm> findAllAlarms(Alarm criteriaObj , Pagination pagination );  // Find All Alarms using Filtering and Pagination
    void generateNewAlarm(); // Generate New Alarm
    void ceaseRandomActiveAlarm();
}
