package com.K.government.bean;

import java.util.ArrayList;
import java.util.List;

public class DataDictNode {
    private String id;              // 唯一标识 (例如 db_gmall, tb_gmall_user)
    private String label;           // 显示名称
    private String type;            // 层级类型: db, table, column
    private String originalComment; // Hive原注释
    private String customComment;   // 用户自定义注释
    
    // 隐藏的业务字段，用于保存时传给后端
    private String dbName;
    private String tbName;
    private String colName;
    
    private List<DataDictNode> children = new ArrayList<>();

    // ----- Getters & Setters -----
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getOriginalComment() { return originalComment; }
    public void setOriginalComment(String originalComment) { this.originalComment = originalComment; }
    public String getCustomComment() { return customComment; }
    public void setCustomComment(String customComment) { this.customComment = customComment; }
    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }
    public String getTbName() { return tbName; }
    public void setTbName(String tbName) { this.tbName = tbName; }
    public String getColName() { return colName; }
    public void setColName(String colName) { this.colName = colName; }
    public List<DataDictNode> getChildren() { return children; }
    public void setChildren(List<DataDictNode> children) { this.children = children; }
}