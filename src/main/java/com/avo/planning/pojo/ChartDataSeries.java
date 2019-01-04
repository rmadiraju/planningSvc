package com.avo.planning.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartDataSeries {


    List<ChartData> dataSets = new ArrayList();
    List<String> labels = new ArrayList<>();

    public ChartDataSeries(List<ChartData> dataItems, List<String> labels) {
        this.dataSets = dataItems;
        this.labels = labels;
    }

    public ChartDataSeries() {
    }

    @JsonProperty("datasets")
    public List<ChartData> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<ChartData> dataSets) {
        this.dataSets = dataSets;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
