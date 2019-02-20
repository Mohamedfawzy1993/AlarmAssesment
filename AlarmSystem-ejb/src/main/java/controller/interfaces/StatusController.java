package controller.interfaces;

import model.dto.Pagination;
import model.dto.ResultSet;
import model.dto.Status;

public interface StatusController {

    ResultSet<Status> findAlarmStatuses(long AlarmID , Pagination pagination); //Find Alarm Statuses Paginated
    ResultSet<Status> findAlarmCurrentStatus(long AlarmID); // Find Alarm Current Status
}
