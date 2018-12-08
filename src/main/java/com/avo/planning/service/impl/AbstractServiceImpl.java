package com.avo.planning.service.impl;

import com.avo.planning.service.*;
import com.avo.planning.service.InstrumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractServiceImpl {
    @Autowired
    protected AttributeService attributeService;

    @Autowired
    protected CampaignService campaignService;

    @Autowired
    protected CalendarService calendarService;

    @Autowired
    protected InstrumentService instrumentService;

    @Autowired
    protected TemplateService templateService;

    @Autowired
    protected CalendarTypeService calendarTypeService;

    @Autowired
    protected CampaignTypeService campaignTypeService;

    @Autowired
    protected InstrumentTypeService instrumentTypeService;
}
