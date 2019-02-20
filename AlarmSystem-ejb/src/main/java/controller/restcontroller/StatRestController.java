package controller.restcontroller;


import controller.impl.AlarmControllerImpl;
import model.dto.ResultSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("statistics")
public class StatRestController {

    @Inject
    private AlarmControllerImpl alarmController;

    @Path("alarmSeverity")
    @GET
    public ResultSet findAlarmBySeverity(){
        return this.alarmController.findAlarmsCountBySeverity();
    }

    @Path("activeAndCeased")
    @GET
    public ResultSet findActiveAndCeasedStat(){
        return this.alarmController.findAlarmsActiveVsCeased();
    }

    @Path("topSites")
    @GET
    public ResultSet findTopAlarmSites(){
        return this.alarmController.findTopAlarmSites();
    }
}
