package by.ita.je.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceClock {
    public ZonedDateTime getZonedTime(){
        return ZonedDateTime.now();
    }

    /*public ZonedDateTime getZonedTimeString(){
        return getZonedTime().format(DateTimeFormatter).toString();
    }*/
}
