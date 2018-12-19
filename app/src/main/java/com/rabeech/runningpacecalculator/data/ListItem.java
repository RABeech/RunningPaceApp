package com.rabeech.runningpacecalculator.data;

public class ListItem {

    private String sr_time;
    private String sr_hour;
    private String sr_minute;
    private String sr_second;
    private String sr_distance;
    private String sr_date;
    private String id;

    public ListItem(){
    }

    public ListItem(String sr_date, String sr_time, String id) {
        this.sr_date = sr_date;
        this.sr_time = sr_time;
        this.id = id;
    }

    public String getSr_time() {
        return sr_time;
    }

    public void setSr_time(String sr_time) {
        this.sr_time = sr_time;
    }

    public String getSr_hour() {
        return sr_hour;
    }

    public void setSr_hour(String sr_hour) {
        this.sr_hour = sr_hour;
    }

    public String getSr_minute() {
        return sr_minute;
    }

    public void setSr_minute(String sr_minute) {
        this.sr_minute = sr_minute;
    }

    public String getSr_second() {
        return sr_second;
    }

    public void setSr_second(String sr_second) {
        this.sr_second = sr_second;
    }

    public String getSr_distance() {
        return sr_distance;
    }

    public void setSr_distance(String sr_distance) {
        this.sr_distance = sr_distance;
    }

    public String getSr_date() {
        return sr_date;
    }

    public void setSr_date(String sr_date) {
        this.sr_date = sr_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
