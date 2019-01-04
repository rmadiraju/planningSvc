package com.avo.planning.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//
// label: "EMail",
//     borderColor: chartColor,
//     fill: true,
//     backgroundColor: gradientFill,
//     borderWidth: 2,
//     data: [40, 26, 28, 45, 20, 25, 30, 25, 20, 25, 20, 15]

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ChartData implements Serializable {

    private String label;
    private boolean fill = true;
    private String backgroundColor  = "#FFFFCC";
    private List<Double> data = new ArrayList<>();

    public ChartData() {
    }

    public ChartData(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
