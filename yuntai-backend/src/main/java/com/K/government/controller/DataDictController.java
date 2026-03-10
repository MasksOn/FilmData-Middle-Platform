package com.K.government.controller;

import com.K.government.bean.DataDictNode;
import com.K.government.service.DataDictService;
import com.K.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class DataDictController {

    // 注入 Service
    @Autowired
    private DataDictService dataDictService;

    /**
     * 获取树状字典数据
     */
    @GetMapping("/tree")
    public Result<List<DataDictNode>> getDictionaryTree() {
        try {
            List<DataDictNode> tree = dataDictService.getDictionaryTree();
            return Result.of(200, "success", tree);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.of(500, "error", new ArrayList<>());
        }
    }

    /**
     * 保存/更新 自定义注释
     */
    @PostMapping("/saveComment")
    public Result<String> saveComment(@RequestBody Map<String, String> payload) {
        String dbName = payload.get("dbName");
        String tbName = payload.get("tbName");
        String colName = payload.getOrDefault("colName", ""); // 表注释时为空
        String comment = payload.get("comment");

        // 基础参数校验
        if (dbName == null || tbName == null) {
            return Result.of(400, "error", "数据库名和表名不能为空");
        }

        try {
            dataDictService.saveCustomComment(dbName, tbName, colName, comment);
            return Result.of(200, "success", "注释设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.of(500, "error", "保存失败：" + e.getMessage());
        }
    }
}