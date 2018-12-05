package com.avo.planning.web.rest;


import com.avo.planning.domain.Calendar;
import com.avo.planning.service.CalendarService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@SuppressWarnings("Duplicates")
public class ServiceLoadTest {


    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/loadSampleData/{value}")
    public String loadTest(@PathVariable String value){

        JSONObject allCalendars = new JSONObject();
        JSONArray calArray = new JSONArray();
        allCalendars.put("allCalendars", calArray);

        for (int i = 1; i<= Integer.valueOf(value); i++) {
            JSONObject calObject = new JSONObject();
            calObject.put("name", "Sample Calendar - "+ i);
            calObject.put("createDate","2018-12-03");
            calObject.put("lastModified","2018-12-03");
            calObject.put("description","Description for Sample Calendar - " + i);

            //Attributes
            JSONArray attributesArray = new JSONArray();
            JSONObject ao1 = new JSONObject();
            JSONObject ao2 = new JSONObject();
            attributesArray.put(ao1);
            attributesArray.put(ao2);

            ao1.put("entityType" , "CALENDAR");
            ao1.put("name" , "Priority");
            ao1.put("type" , "STRING");
            ao1.put("value" , "High");

            ao2.put("entityType" , "CALENDAR");
            ao2.put("name" , "IsActive");
            ao2.put("type" , "BOOLEAN");
            ao2.put("value" , "true");
            calObject.put("attributes", attributesArray);

            //CalendarType
            JSONObject calTypeObject = new JSONObject();
            calTypeObject.put("name", "SEASONAL");
            calTypeObject.put("scope", "GLOBAL");
            calObject.put("calendarType", calTypeObject);

            //SourceTemplate
            JSONObject sTempl = new JSONObject();
            sTempl.put("name", "SESONAL-CAL-TEMPL");
            sTempl.put("description", "Seasonal Calendar Template");
            sTempl.put("type", "CALENDAR");
            sTempl.put("createDate","2018-12-03");
            sTempl.put("lastModified","2018-12-03");
            sTempl.put("isActive" , "true");
            calObject.put("sourceTemplate", sTempl);

            //Attributes For Template

            //Attributes
            JSONArray templAttributesArray = new JSONArray();
            JSONObject tao1 = new JSONObject();
            templAttributesArray.put(tao1);

            tao1.put("entityType" , "TEMPLATE");
            tao1.put("name" , "Calendar Seasonal Attribute");
            tao1.put("type" , "STRING");
            tao1.put("value" , "Some Value for Seasonal attribute");


            calObject.put("attributes", templAttributesArray);

            // Add it to the Calendar Array
            calArray.put(calObject);

            /*
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(calObject.toString(), headers);

            restTemplate.exchange(
                "http://localhost:9080/api/calendars", HttpMethod.PUT, entity, String.class).getBody();
            */

        }

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(calArray.toString(), headers);

        restTemplate.exchange(
            "http://localhost:9080/api/calendars/list", HttpMethod.POST, entity, String.class).getBody();


        JSONObject allCampaigns = new JSONObject();
        JSONArray campArray = new JSONArray();
        allCampaigns.put("allCampaigns", campArray);

        int calCounter = 1;
        for (int i = 1; i<= Integer.valueOf(value)*10; i++) {


            if (i%10 == 0)
                calCounter++;

            JSONObject campObject = new JSONObject();
            campObject.put("name", "Sample Campaign - "+ i);
            campObject.put("createDate","2018-12-03");
            campObject.put("lastModified","2018-12-03");
            campObject.put("description","Description for Sample Campaign - " + i);
            campObject.put("calendar","Sample Calendar - "+calCounter);

            //Attributes
            JSONArray attributesArrayc = new JSONArray();
            JSONObject cao1 = new JSONObject();
            JSONObject cao2 = new JSONObject();
            attributesArrayc.put(cao1);
            attributesArrayc.put(cao2);

            cao1.put("entityType" , "CAMPAIGN");
            cao1.put("name" , "Campaign Attribute");
            cao1.put("type" , "STRING");
            cao1.put("value" , "Some Value");

            cao2.put("entityType" , "CAMPAIGN");
            cao2.put("name" , "Campaign Attribute 2");
            cao2.put("type" , "INT");
            cao2.put("value" , "10");
            campObject.put("attributes", attributesArrayc);

            //CampaignType
            JSONObject campTypeObject = new JSONObject();
            campTypeObject.put("name", "Yearly Campaign");
            campTypeObject.put("recurring", "true");
            campTypeObject.put("recurring", "true");
            campTypeObject.put("type", "FLYER");
            campObject.put("campaignType", campTypeObject);


            //SourceTemplate
            JSONObject sTempl = new JSONObject();
            sTempl.put("name", "CAMPAIGN-TEMPL");
            sTempl.put("description", "Campaign Template");
            sTempl.put("type", "CAMPAIGN");
            sTempl.put("createDate","2018-12-03");
            sTempl.put("lastModified","2018-12-03");
            sTempl.put("isActive" , "true");
            campObject.put("sourceTemplate", sTempl);

            //Attributes For Template

            //Attributes
            JSONArray templAttributesArray = new JSONArray();
            JSONObject tao1 = new JSONObject();
            templAttributesArray.put(tao1);

            tao1.put("entityType" , "TEMPLATE");
            tao1.put("name" , "Calendar Seasonal Attribute");
            tao1.put("type" , "STRING");
            tao1.put("value" , "Some Value for Seasonal attribute");


            campObject.put("attributes", templAttributesArray);

            // Add Instruments


            JSONArray instrArray = new JSONArray();

            for (int j=0;j<=5; j ++) {
                JSONObject instObject = new JSONObject();
                instObject.put("name", "Sample Instrument - " + i+" "+j);
                instObject.put("createDate", "2018-12-03");
                instObject.put("lastModified", "2018-12-03");
                instObject.put("startDate", "2018-12-03");
                instObject.put("endDate", "2019-12-03");
                instObject.put("description", "Description for Sample Instrument - "+ i +" "+j);
                instrArray.put(instObject);
            }
            campObject.put("instruments", instrArray);

            // Add it to the Calendar Array
            campArray.put(campObject);

        }

        restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        entity = new HttpEntity<>(campArray.toString(), headers);

        restTemplate.exchange(
            "http://localhost:9080/api/campaigns/list", HttpMethod.POST, entity, String.class).getBody();



        return "Successfully created "+value+" Calendars";
    }
}
