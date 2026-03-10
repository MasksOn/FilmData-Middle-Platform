package com.K.statistic.service;

public class DATABASE {
    public static String MYSQL_URL = "jdbc:mysql://192.168.235.130:3306/ads_baize_dw?useUnicode=true&characterEncoding=utf8&useSSL=false";
    public static String MYSQL_USERNAME = "root";
    public static String MYSQL_PASSWORD = "123456";
    public static String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String CLICKHOUSE_URL = "jdbc:clickhouse://192.168.235.132:8123/ads_baize_dw?compress=0";
    public static String CLICKHOUSE_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
    public static String CLICKHOUSE_USERNAME = "default";
    public static String CLICKHOUSE_PASSWORD = "123456";
}
