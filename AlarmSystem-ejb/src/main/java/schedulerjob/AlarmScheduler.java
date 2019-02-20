package schedulerjob;

import controller.impl.AlarmControllerImpl;
import model.dao.AlarmDao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;

@Startup
@Singleton
public class AlarmScheduler  {

    private boolean isRunning ;

    public boolean isRunning() {
        return isRunning;
    }

    @Resource
    private TimerService timerService;

    @Inject
    AlarmControllerImpl alarmController;

    @PostConstruct
    private void initialize() {
        this.createTimer(10000);

    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        if(timer.getInfo().equals("Alarm"))
            this.alarmController.generateNewAlarm();
        if(timer.getInfo().equals("cease"))
            this.alarmController.ceaseRandomActiveAlarm();
    }

    public void terminateTimer(){
        for(Timer timer : timerService.getTimers())
            if (timer.getInfo().equals("Alarm"))
                timer.cancel();
        for(Timer timer : timerService.getTimers()) {
            if (timer.getInfo().equals("cease"))
                timer.cancel();
        }
        System.out.println("Terminated");
        isRunning = false;
    }

    public void createTimer(long interval){
        if(interval <= 0)
            interval = 1000;
        timerService.createTimer(0, interval, "Alarm");
        timerService.createTimer(0 , interval*2 , "cease");
        System.out.println("Created");
        isRunning = true;

    }
}
