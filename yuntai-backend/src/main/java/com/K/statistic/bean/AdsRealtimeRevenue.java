package com.K.statistic.bean;


public class AdsRealtimeRevenue {
    /**
     * 交易日期 (yyyy-MM-dd)
     */
    private String dt;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 交易总金额
     */
    private Double totalAmount;
}