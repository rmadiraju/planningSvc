package com.avo.planning.startup;

import com.avo.planning.domain.*;
import com.avo.planning.domain.enumeration.CalendarScopeEnum;
import com.avo.planning.domain.enumeration.CampaignTypeEnum;
import com.avo.planning.domain.enumeration.DeadlineTypeEnum;
import com.avo.planning.domain.enumeration.InstrumentTypeEnum;
import com.avo.planning.repository.CalendarRepository;
import com.avo.planning.repository.CalendarTypeRepository;
import com.avo.planning.util.HTMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;
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

        // Delete campaigns and Instruments
        campaignTypeRepository.deleteAll();
        campaignRepository.deleteAll();

        instrumentTypeRepository.deleteAll();
        instrumentRepository.deleteAll();

        deadlineRepository.deleteAll();
        deadlineRepository.deleteAll();


    }

    public void createBaseData() {

        if (calendarTypeRepository.findAll().isEmpty()) {
            log.info("Adding test calendar type");
            CalendarType calendarType = new CalendarType();
            calendarType.setName("BaseCalendarType");
            calendarType.scope(CalendarScopeEnum.GLOBAL);
            calendarTypeRepository.save(calendarType);
        }

        if (campaignRepository.findAll().isEmpty()) {
            log.info("Adding test Campaign types");
            CampaignType campaignType = new CampaignType();
            campaignType.setName("Weekly Campaign");
            campaignType.setRecurring(true);
            campaignType.setType(CampaignTypeEnum.WEEKLY);
            campaignTypeRepository.save(campaignType);

            CampaignType campaignType2 = new CampaignType();
            campaignType2.setName("Seasonal Campaign");
            campaignType2.setRecurring(false);
            campaignType2.setType(CampaignTypeEnum.SEASONAL);
            campaignTypeRepository.save(campaignType2);

            CampaignType campaignType3 = new CampaignType();
            campaignType3.setName("Yearly Campaign");
            campaignType3.setRecurring(false);
            campaignType3.setType(CampaignTypeEnum.YEARLY);
            campaignTypeRepository.save(campaignType3);
        }

        if (instrumentRepository.findAll().isEmpty()){
            log.info("Adding Instrument Types");
            InstrumentType instrumentType = new InstrumentType();
            instrumentType.setName("flyer");
            instrumentType.setType(InstrumentTypeEnum.FLYER);
            instrumentTypeRepository.save(instrumentType);

            InstrumentType instrumentType2 = new InstrumentType();
            instrumentType2.setName("email");
            instrumentType2.setType(InstrumentTypeEnum.EMAIL);
            instrumentTypeRepository.save(instrumentType2);

            InstrumentType instrumentType3 = new InstrumentType();
            instrumentType3.setName("coupon");
            instrumentType3.setType(InstrumentTypeEnum.COUPON);
            instrumentTypeRepository.save(instrumentType3);

            InstrumentType instrumentType4 = new InstrumentType();
            instrumentType4.setName("web");
            instrumentType4.setType(InstrumentTypeEnum.WEB);
            instrumentTypeRepository.save(instrumentType4);
        }

        if (deadlineTypeRepository.findAll().isEmpty()) {
            DeadlineType deadlineType = new DeadlineType();
            deadlineType.setName("Offer Deadline");
            deadlineType.setType(DeadlineTypeEnum.OFFER);
            deadlineTypeRepository.save(deadlineType);
            DeadlineType deadlineType2 = new DeadlineType();
            deadlineType2.setName("Space Allocation");
            deadlineType2.setType(DeadlineTypeEnum.SPACEALLOCATION);
            deadlineTypeRepository.save(deadlineType2);
            DeadlineType deadlineType3 = new DeadlineType();
            deadlineType3.setName("Assign Targets");
            deadlineType3.setType(DeadlineTypeEnum.ASSIGNTARGETS);
            deadlineTypeRepository.save(deadlineType3);


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
                List<Campaign> campaignList = new ArrayList<>();
                log.info("Adding test Campaigns for Calendar {}", i);
                for (int j=0; j < 3; j++){
                    Campaign campaign = new Campaign();
                    campaign.setName("Campaign "+ i+"-"+j);
                    campaign.setCampaignType(campaignTypeRepository.findAll().get(j));
                    campaign.setActive(true);
                    campaign.setDescription("Test Campaign "+j);
                    campaign.setCreateDate(LocalDate.now());
                    campaign.setCalendar(calendar.getName());
                    campaign.setStartDate(LocalDate.now().plusDays(10));
                    campaign.setEndDate(campaign.getStartDate().plusDays(7));
                    campaign.setActive(true);
                    campaign.setTemplate(false);
                    campaignList.add(campaign);
                    log.info("Adding test Instruments for Campaign {} ", j);
                    List<Instrument> instrumentList = new ArrayList<>();
                    for (int k = 0; k < 2; k++){
                        Instrument instrument = new Instrument();
                        instrument.setName("Instrument "+ i+"-"+j+"-"+k);
                        instrument.setInstrumentType(instrumentTypeRepository.findAll().get(k));
                        instrument.setCreateDate(LocalDate.now());
                        instrument.setStartDate(LocalDate.now().plusDays(10));
                        instrument.setEndDate(LocalDate.now().plusDays(17));
                        instrumentList.add(instrument);
                    }
                    instrumentRepository.saveAll(instrumentList);
                    campaign.getInstruments().addAll(instrumentList);
                    campaign.getInstruments().forEach(inst->  inst.setId(instrumentRepository.findFirstByName(inst.getName()).getId()));
                }

                campaignRepository.saveAll(campaignList);

            }
            calendarRepository.saveAll(calendarList);
        }

        log.info("Test data info\n=====================\nCalendarTypes: {}\nCalendars: {}\n",calendarTypeRepository.count(),calendarRepository.count());


    }
}
