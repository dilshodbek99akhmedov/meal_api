package team.concurrency.mealproject.service.cronJob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJob {

    @Scheduled(cron="0 10 7-9 * * *")
    public void writeCronJob(){
        //do were
    }
}
