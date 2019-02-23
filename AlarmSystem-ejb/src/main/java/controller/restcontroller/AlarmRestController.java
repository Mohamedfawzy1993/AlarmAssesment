package controller.restcontroller;


import controller.impl.AlarmControllerImpl;
import controller.websocket.WebSocketServer;
import model.entities.Alarm;
import model.entities.Pagination;
import model.entities.ResultSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Stateless
@Path("alarm")
public class AlarmRestController {

    @Inject
    private AlarmControllerImpl alarmController;

    @GET
    public ResultSet<Alarm> findAlarmByID(@QueryParam("id") String alarmID ,
                                          @QueryParam("currentPage") int currentPage,
                                          @QueryParam("pageSize") int pageSize){
        Pagination pagination = null;
        if(pageSize != 0)
            pagination = new Pagination(pageSize , 0,currentPage);
        ResultSet<Alarm> alarmResultSet = alarmController.searchAlarmByID(alarmID ,pagination);
        alarmResultSet.getPagination().setCurrentPage(currentPage);
        alarmResultSet.getPagination().setPageSize(pageSize);
        return alarmResultSet;
    }

    @GET
    @Path("all")
    public ResultSet<Alarm> findAllAlarms(@QueryParam("id") String alarmID,
                                          @QueryParam("site") String site,
                                          @QueryParam("severity") String severity,
                                          @QueryParam("description") String description,
                                          @QueryParam("isActive") String isActive,
                                          @QueryParam("currentPage") int currentPage,
                                          @QueryParam("pageSize") int pageSize) {
        Alarm alarm = new Alarm();

        Pagination pagination = new Pagination(pageSize , 0 , currentPage);
        if(alarmID != null)
            alarm.setAlarmId(alarmID);
        if(site != null)
            alarm.setSiteId(site);
        if(severity != null)
            alarm.setSeverity(severity);
        if(description != null)
            alarm.setDescription(description);
        if(isActive != null)
            alarm.setIsActive(Integer.parseInt(isActive));

        ResultSet<Alarm> alarmResultSet = this.alarmController.findAllAlarms(alarm , pagination);
        return alarmResultSet;


    }


}
