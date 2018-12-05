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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class BaseStartup {

    private static final Logger log = LoggerFactory.getLogger(BaseStartup.class);

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarTypeRepository calendarTypeRepository;

    @PostConstruct
    public void alwaysRun() {
        log.info("Running base startup tasks...");
        // create base data
        createBaseData();
    }

    public BaseStartup() {

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
            for (int i = 0; i < 50; i++) {
                Calendar calendar = new Calendar();
                calendar.setName("Calendar " + i);
                calendar.setCreateDate(LocalDate.now());
                calendar.setDescription("Cal" + i);
                calendar.setColour(HTMLUtils.getRandomHexColour());
                calendar.setIcon("testIcon");
                calendar.setCalendarType(calendarType);
                calendarList.add(calendar);
            }
            calendarRepository.saveAll(calendarList);
        }

        log.info("Test data info\n=====================\nCalendarTypes: {}\nCalendars: {}\n",calendarTypeRepository.count(),calendarRepository.count());


    }
}
