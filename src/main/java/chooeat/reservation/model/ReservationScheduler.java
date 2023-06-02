package chooeat.reservation.model;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationScheduler {

    @Scheduled(cron = "0 0 * * * *") // 每小时的整点触发一次
    public void invokeMethodForExpiredReservations() {
        // 在这里编写需要调用的方法的逻辑
        // 该方法将在reservation_date_starttime字段中的时间点的24小时前被调用
    }
}
