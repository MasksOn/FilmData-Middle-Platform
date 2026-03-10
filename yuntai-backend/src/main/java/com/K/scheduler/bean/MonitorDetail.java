package com.K.scheduler.bean;

public class MonitorDetail {
    private String databaseName;
    private String tableName;
    private String fieldName;
    private Double fieldNullRate;
    private String dt; // 🌟 1. 必须声明这个属性
    public MonitorDetail() {
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldNullRate() {
        return fieldNullRate;
    }

    public void setFieldNullRate(Double fieldNullRate) {
        this.fieldNullRate = fieldNullRate;
    }

    @Override
    public String toString() {
        return "MonitorDetail{" +
                "databaseName='" + databaseName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldNullRate=" + fieldNullRate +
                ", dt='" + dt + '\'' +
                '}';
    }

    // 🌟 2. 补充完整的 get/set 方法
    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt; // 🌟 这里必须赋值
    }
}
