package com.avo.planning.pojo;

//     pointBorderColor: chartColor,
//     pointBackgroundColor: "#2c2c2c",
//     pointHoverBackgroundColor: "#2c2c2c",
//     pointHoverBorderColor: chartColor,
//     pointBorderWidth: 1,
//     pointHoverRadius: 7,
//     pointHoverBorderWidth: 2,
//     pointRadius: 5,

public class LineChartData extends ChartData {

    private String pointBorderColor= "#2c2c2c";
    private String pointBackgroundColor = "#2c2c2c";
    private String pointHoverBackgroundColor = "#FFFFCC";
    private String pointHoverBorderColor = "#FFFFCC";
    private int pointBorderWidth = 1;
    private int pointHoverRadius = 7;
    private int pointHoverBorderWidth = 2;
    private int pointRadius = 5;

    public LineChartData() {
    }

    public LineChartData(String label) {
        super(label);
    }

    public String getPointBorderColor() {
        return pointBorderColor;
    }

    public void setPointBorderColor(String pointBorderColor) {
        this.pointBorderColor = pointBorderColor;
    }

    public String getPointBackgroundColor() {
        return pointBackgroundColor;
    }

    public void setPointBackgroundColor(String pointBackgroundColor) {
        this.pointBackgroundColor = pointBackgroundColor;
    }

    public String getPointHoverBackgroundColor() {
        return pointHoverBackgroundColor;
    }

    public void setPointHoverBackgroundColor(String pointHoverBackgroundColor) {
        this.pointHoverBackgroundColor = pointHoverBackgroundColor;
    }

    public String getPointHoverBorderColor() {
        return pointHoverBorderColor;
    }

    public void setPointHoverBorderColor(String pointHoverBorderColor) {
        this.pointHoverBorderColor = pointHoverBorderColor;
    }

    public int getPointBorderWidth() {
        return pointBorderWidth;
    }

    public void setPointBorderWidth(int pointBorderWidth) {
        this.pointBorderWidth = pointBorderWidth;
    }

    public int getPointHoverRadius() {
        return pointHoverRadius;
    }

    public void setPointHoverRadius(int pointHoverRadius) {
        this.pointHoverRadius = pointHoverRadius;
    }

    public int getPointHoverBorderWidth() {
        return pointHoverBorderWidth;
    }

    public void setPointHoverBorderWidth(int pointHoverBorderWidth) {
        this.pointHoverBorderWidth = pointHoverBorderWidth;
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
    }
}
