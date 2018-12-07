package com.avo.planning.startup;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.CalendarType;
import com.avo.planning.domain.enumeration.CalendarScopeEnum;
import com.avo.planning.repository.CalendarRepository;
import com.avo.planning.repository.CalendarTypeRepository;
import com.avo.planning.util.HTMLUtils;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public abstract class BaseStartup {

    private static final Logger log = LoggerFactory.getLogger(BaseStartup.class);

    @Autowired
    protected CalendarRepository calendarRepository;

    @Autowired
    protected CalendarTypeRepository calendarTypeRepository;





    public BaseStartup() {
        log.info("Running base startup tasks...");

    }


}
