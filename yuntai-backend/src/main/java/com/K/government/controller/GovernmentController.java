package com.K.government.controller;

import com.K.government.bean.GovernmentDetail;
import com.K.government.service.GovernmentService;
import com.K.scheduler.bean.MonitorDetail;
import com.K.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/government")
public class GovernmentController {

    // 🌟 注入刚刚抽离出来的 Service
    @Autowired
    private GovernmentService governmentService;

    /**
     * 获取血缘关系
     */
    @GetMapping("lineage")
    public String getLineage() {
        // 直接调用 Service
        return governmentService.getLineage();
    }

    /**
     * 获取 Hive 元数据质量监控数据
     */
    @GetMapping("hiveMetadataScore")
    public Result<List<GovernmentDetail>> getHiveMetadataScore() {
        try {
            List<GovernmentDetail> result = governmentService.getHiveMetadataScore();
            return Result.of(200, "success", result);
        } catch (Exception e) {
            e.printStackTrace();
            // 保持你原有的逻辑：报错时依然返回状态200和空数组，防止前端页面彻底崩溃
            return Result.of(200, "success", new ArrayList<>());
        }
    }

    /**
     * 获取 MySQL 数据质量监控数据
     */
    @GetMapping("mysqlDataMonitor")
    public Result<List<MonitorDetail>> getMySQLDataMonitor() {
        try {
            List<MonitorDetail> result = governmentService.getMySQLDataMonitor();
            return Result.of(200, "success", result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.of(200, "success", new ArrayList<>());
        }
    }
}