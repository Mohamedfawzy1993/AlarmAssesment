package schedulerjob;

import controller.impl.AlarmControllerImpl;
import model.dao.AlarmDao;
import util.DateUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class AlarmScheduler  {

    private boolean isRunning ;
    public static List<String> serverLog = new ArrayList<>();

    public boolean isRunning() {
        return isRunning;
    }

    @Resource
    private TimerService timerService;

    @Inject
    AlarmControllerImpl alarmController;

    @PostConstruct
    private void initialize() {
        this.createTimer(10000 , true);

    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        if(timer.getInfo().equals("Alarm"))
            this.alarmController.generateNewAlarm();
        if(timer.getInfo().equals("cease"))
            this.alarmController.ceaseRandomActiveAlarm();
    }

    public List<String> terminateTimer(boolean log){
        for(Timer timer : timerService.getTimers())
            if (timer.getInfo().equals("Alarm"))
                timer.cancel();
        for(Timer timer : timerService.getTimers()) {
            if (timer.getInfo().equals("cease"))
                timer.cancel();
        }
        System.out.println("Terminated");
        isRunning = false;

        if(log)
            AlarmScheduler.serverLog.add("Alarm Timer Terminated @ "+ DateUtil.LocalDateTimeFormat(LocalDateTime.now()));
        return AlarmScheduler.serverLog;
    }

    public List<String> createTimer(long interval , boolean log){
        if(interval <= 0)
            interval = 1000;
        timerService.createTimer(0, interval, "Alarm");
        timerService.createTimer(0 , interval*2 , "cease");
        System.out.println("Created");
        isRunning = true;
        if (log)
        AlarmScheduler.serverLog.add("Alarm Timer Created With Interval of "
                + interval/1000  + " Seconds @ "+ DateUtil.LocalDateTimeFormat(LocalDateTime.now()));
        return AlarmScheduler.serverLog;

    }

}
