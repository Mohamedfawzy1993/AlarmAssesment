package controller.impl;

import model.entities.Pagination;
import model.entities.ResultSet;
import model.entities.Status;

import javax.ejb.Local;

public class StatusControllerImpl {
    public ResultSet<Status> findAlarmStatuses(long AlarmID, Pagination pagination) {
        return null;
    }

    public ResultSet<Status> findAlarmCurrentStatus(long AlarmID) {
        return null;
    }
}
