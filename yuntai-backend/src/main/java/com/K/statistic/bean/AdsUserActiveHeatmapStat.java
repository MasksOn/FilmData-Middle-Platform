package com.K.statistic.bean;
public class AdsUserActiveHeatmapStat {
    private Integer dayOfWeek;
    private Integer hourOfDay;
    private Long interactionCount;
    private String dt;

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(Integer hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getInteractionCount() {
        return interactionCount;
    }

    public void setInteractionCount(Long interactionCount) {
        this.interactionCount = interactionCount;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}