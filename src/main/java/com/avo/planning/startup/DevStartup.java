package com.avo.planning.startup;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.CalendarType;
import com.avo.planning.domain.enumeration.CalendarScopeEnum;
import com.avo.planning.repository.CalendarRepository;
import com.avo.planning.repository.CalendarTypeRepository;
import com.avo.planning.util.HTMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Profile("dev")
@Controller
@Configuration
public class DevStartup extends BaseStartup {
    private static final Logger log = LoggerFactory.getLogger(DevStartup.class);

    @PostConstruct
    public void devStartup() {
        log.info("Running base startup tasks...");
        // create base data
        clearDevData();
        createBaseData();
    }
    
    private void clearDevData() {
        log.debug("Clearing all calendars...");
        calendarTypeRepository.deleteAll();
        calendarRepository.deleteAll();
    }

    public void createBaseData() {

        if (calendarTypeRepository.findAll().isEmpty()) {
            log.info("Adding test calendar type");
            CalendarType calendarType = new CalendarType();
            calendarType.setName("BaseCalendarType");
            calendarType.scope(CalendarScopeEnum.GLOBAL);
            calendarTypeRepository.save(calendarType);
        }

        CalendarType calendarType = calendarTypeRepository.findAll().get(0);

        if (calendarRepository.findAll().isEmpty()) {
            log.info("Adding test calendars");
            List<Calendar> calendarList = new ArrayList();
            for (int i = 0; i < 21; i++) {
                Calendar calendar = new Calendar();
                calendar.setName("Calendar " + i);
                calendar.setCreateDate(LocalDate.now());
                calendar.setDescription("Cal" + i);
                calendar.setColour(HTMLUtils.getRandomHexColour());
                calendar.setIcon("testIcon");
                calendar.setActive(true);
                calendar.setCalendarType(calendarType);
                if ( i % 5 == 0 ) {
                    calendar.setStartDate(LocalDate.now());
                    calendar.setEndDate(LocalDate.now().plus(10 + i, ChronoUnit.DAYS));
                }
                calendarList.add(calendar);

            }
            calendarRepository.saveAll(calendarList);
        }

        log.info("Test data info\n=====================\nCalendarTypes: {}\nCalendars: {}\n",calendarTypeRepository.count(),calendarRepository.count());


    }
}
