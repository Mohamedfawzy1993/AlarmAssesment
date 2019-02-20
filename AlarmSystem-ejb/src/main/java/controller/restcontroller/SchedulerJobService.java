package controller.restcontroller;


import schedulerjob.AlarmScheduler;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("scheduler")
@Stateless
public class SchedulerJobService {

    @Inject
    AlarmScheduler alarmScheduler;

    @POST
    @Path("alarm/start")
    public void startAlarmJob(@QueryParam("interval") long interval){
        alarmScheduler.terminateTimer();
        alarmScheduler.createTimer(interval);
    }

    @POST
    @Path("alarm/stop")
    public void stopAlarmJob(){
        alarmScheduler.terminateTimer();
    }

    @Path("alarm/status")
    @GET
    public String currentScheduleStatus(){
        return "{ \"isRunning\" : "+ (alarmScheduler.isRunning() ? 1 : 0) + "}";
    }

}
