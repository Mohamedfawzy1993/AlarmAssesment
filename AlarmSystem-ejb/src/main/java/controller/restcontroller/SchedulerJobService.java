package controller.restcontroller;


import schedulerjob.AlarmScheduler;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("scheduler")
@Stateless
public class SchedulerJobService {


    @Inject
    AlarmScheduler alarmScheduler;

    @POST
    @Path("alarm/start")
    public List<String> startAlarmJob(@QueryParam("interval") long interval){
        alarmScheduler.terminateTimer(false);
        List<String> stringList = alarmScheduler.createTimer(interval , true);
        return stringList;
    }

    @POST
    @Path("alarm/stop")
    public List<String> stopAlarmJob(){

        List<String> stringList = alarmScheduler.terminateTimer(true);
        return stringList;
    }

    @Path("alarm/status")
    @GET
    public String currentScheduleStatus(){
        return "{ \"isRunning\" : "+ (alarmScheduler.isRunning() ? 1 : 0) + "}";
    }

    @Path("alarm/log")
    @GET
    public List<String> getLog() {
        return AlarmScheduler.serverLog;
    }
}
