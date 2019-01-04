package com.avo.planning.web.rest;

import com.avo.planning.pojo.ChartData;
import com.avo.planning.pojo.ChartDataSeries;
import com.avo.planning.pojo.LineChartData;
import com.avo.planning.util.HTMLUtils;
import com.avo.planning.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "http://localhost:3000")
public class MetricsResource {

    private static final int max = 20;
    private static final int min = 0;
    private final Logger log = LoggerFactory.getLogger(MetricsResource.class);
    private Random random = new Random();

    @GetMapping("/rollingCampaignCountByInstrumentType")
    @Timed
    public ResponseEntity<ChartDataSeries> rollingCampaignCountByInstrumentType() {
        log.debug("REST request to get a page of UserInfos");
        ChartDataSeries responseData = new ChartDataSeries();
        List<ChartData> data = new ArrayList();

        // get next 12 months
        LocalDate now = LocalDate.now();

        for (int x = 0; x < 12; x++) {
            Month currentMonth = now.getMonth();
            responseData.getLabels().add(currentMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            now = now.plusMonths(1l);
        }


        for (int i = 1; i <= 5; i++) {
            LineChartData d = new LineChartData("I" + i);
            d.setPointBackgroundColor(HTMLUtils.getRandomHexColour());

            for (int x = 0; x < 12; x++) {
                d.getData().add(new Double(random.nextInt(max - min + 1) + min));
            }
            data.add(d);
        }

        responseData.setDataSets(data);

        final HttpHeaders chartHeaders = HeaderUtil.createChartHeaders("req", "rollingCampaignCountByInstrumentType");
        return ResponseEntity.ok().headers(chartHeaders).body(responseData);
    }

}
