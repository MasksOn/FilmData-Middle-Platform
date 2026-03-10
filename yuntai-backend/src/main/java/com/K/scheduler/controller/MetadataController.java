package com.K.scheduler.controller;

import com.K.util.Result;
import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    // 指向你存储业务数据的 MySQL 地址
    private static final String JDBC_URL = "jdbc:mysql://192.168.235.130:3306/information_schema?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "123456";

    /**
     * 1. 获取所有业务数据库名 (排除系统自带的库)
     */
    @GetMapping("/databases")
    public Result<List<String>> getDatabases() {
        var result = new ArrayList<String>();
        try (var conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             var stmt = conn.prepareStatement("SELECT SCHEMA_NAME FROM SCHEMATA WHERE SCHEMA_NAME NOT IN ('information_schema', 'mysql', 'performance_schema', 'sys')");
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("SCHEMA_NAME"));
            }
            return Result.of(200, "success", result);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.of(500, "error", new ArrayList<>());
        }
    }

    /**
     * 2. 获取指定数据库下的所有表名
     */
    @GetMapping("/tables")
    public Result<List<String>> getTables(@RequestParam String databaseName) {
        var result = new ArrayList<String>();
        try (var conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             var stmt = conn.prepareStatement("SELECT TABLE_NAME FROM TABLES WHERE TABLE_SCHEMA = ?")) {
            stmt.setString(1, databaseName);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("TABLE_NAME"));
                }
            }
            return Result.of(200, "success", result);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.of(500, "error", new ArrayList<>());
        }
    }

    /**
     * 3. 获取指定库、指定表下的所有字段名
     */
    @GetMapping("/columns")
    public Result<List<String>> getColumns(@RequestParam String databaseName, @RequestParam String tableName) {
        var result = new ArrayList<String>();
        try (var conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             var stmt = conn.prepareStatement("SELECT COLUMN_NAME FROM COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?")) {
            stmt.setString(1, databaseName);
            stmt.setString(2, tableName);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("COLUMN_NAME"));
                }
            }
            return Result.of(200, "success", result);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.of(500, "error", new ArrayList<>());
        }
    }
}