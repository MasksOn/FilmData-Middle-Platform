package com.K.report.controller;

import com.K.report.bean.SqlQuery;
import com.K.report.service.CreateTableService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class CreateTableController {

    // 🚀 实例化一个 RestTemplate 用于跨语言微服务调用
    private final RestTemplate restTemplate = new RestTemplate();

    // ⚠️ 指向你 Python FastAPI 运行的真实地址和端口
    private static final String PYTHON_HIVE_URL = "http://localhost:8000/internal/hive/execute";


    // ==========================================
    // 1. MySQL 和 ClickHouse 依然走原本的 JDBC 逻辑
    // ==========================================
    @PostMapping("createClickHouseTable")
    public String createClickHouseTable(@RequestBody SqlQuery sqlQuery) throws Exception {
        return CreateTableService.createClickHouseTable(sqlQuery.getSql());
    }

    @PostMapping("createMySQLTable")
    public String createMySQLTable(@RequestBody SqlQuery sqlQuery) throws Exception {
        return CreateTableService.createMySQLTable(sqlQuery.getSql());
    }

    @PostMapping("queryClickHouse")
    public Result<Map<String, Object>> queryClickHouse(@RequestBody SqlQuery sqlQuery) {
        Map<String, Object> res = CreateTableService.queryClickHouse(sqlQuery.getSql());
        if ("success".equals(res.get("status"))) {
            return Result.of(200, "查询成功", res);
        } else {
            return Result.of(500, (String) res.get("message"), null);
        }
    }

    @PostMapping("queryMySQL")
    public Result<Map<String, Object>> queryMySQL(@RequestBody SqlQuery sqlQuery) {
        Map<String, Object> res = CreateTableService.queryMySQL(sqlQuery.getSql());
        if ("success".equals(res.get("status"))) {
            return Result.of(200, "查询成功", res);
        } else {
            return Result.of(500, (String) res.get("message"), null);
        }
    }


    // ==========================================
    // 2. 🚀 Hive 相关的请求，全部转发给 Python 微服务
    // ==========================================

    @PostMapping("createHiveTable")
    public Result<Map<String, Object>> createHiveTable(@RequestBody SqlQuery sqlQuery) {
        return forwardToPythonEngine(sqlQuery.getSql());
    }

    @PostMapping("queryHive")
    public Result<Map<String, Object>> queryHive(@RequestBody SqlQuery sqlQuery) {
        return forwardToPythonEngine(sqlQuery.getSql());
    }

    /**
     * 核心转发逻辑：Java 作为代理，将 SQL 发给 Python
     */
    private Result<Map<String, Object>> forwardToPythonEngine(String sql) {
        try {
            // 构造要发给 Python 的 JSON 体：{"sql": "..."}
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("sql", sql);

            // 发送 HTTP POST 请求到 Python 服务，并接收 Map 格式的响应
            Map<String, Object> response = restTemplate.postForObject(PYTHON_HIVE_URL, requestBody, Map.class);

            // 解析 Python 返回的 JSON
            if (response != null && "success".equals(response.get("status"))) {
                return Result.of(200, "Hive 引擎执行成功", response);
            } else {
                String errorMsg = response != null ? (String) response.get("message") : "Python 服务未返回明确错误";
                return Result.of(500, "Hive 引擎执行失败: " + errorMsg, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.of(500, "无法连接到 Python 微服务 (请检查 8000 端口是否启动): " + e.getMessage(), null);
        }
    }



}