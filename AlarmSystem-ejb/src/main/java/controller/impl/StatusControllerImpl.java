package controller.impl;

import controller.interfaces.StatusController;
import model.dto.Pagination;
import model.dto.ResultSet;
import model.dto.Status;

import javax.ejb.Local;

@Local(StatusController.class)
public class StatusControllerImpl implements StatusController {
    public ResultSet<Status> findAlarmStatuses(long AlarmID, Pagination pagination) {
        return null;
    }

    public ResultSet<Status> findAlarmCurrentStatus(long AlarmID) {
        return null;
    }
}
