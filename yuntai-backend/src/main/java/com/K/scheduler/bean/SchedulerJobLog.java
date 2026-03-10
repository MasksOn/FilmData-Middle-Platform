package com.K.scheduler.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SchedulerJobLog {
    private Long id;
    private String jobName;
    private String jobGroup;
    
    // 格式化返回给前端的时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date executeTime;
    
    private String status;
    private Long duration;
    private String message;

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }
    public String getJobGroup() { return jobGroup; }
    public void setJobGroup(String jobGroup) { this.jobGroup = jobGroup; }
    public Date getExecuteTime() { return executeTime; }
    public void setExecuteTime(Date executeTime) { this.executeTime = executeTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getDuration() { return duration; }
    public void setDuration(Long duration) { this.duration = duration; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}