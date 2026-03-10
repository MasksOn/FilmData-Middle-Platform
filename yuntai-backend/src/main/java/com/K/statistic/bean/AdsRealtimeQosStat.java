package com.K.statistic.bean;


public class AdsRealtimeQosStat {
    public String getCdnNode() {
        return cdnNode;
    }

    public void setCdnNode(String cdnNode) {
        this.cdnNode = cdnNode;
    }

    public Integer getBufferCount() {
        return bufferCount;
    }

    public void setBufferCount(Integer bufferCount) {
        this.bufferCount = bufferCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * CDN 节点名称 (如: cdn_beijing)
     */
    private String cdnNode;
    
    /**
     * 播放卡顿缓冲次数
     */
    private Integer bufferCount;
    
    /**
     * 播放报错/崩溃次数
     */
    private Integer errorCount;
}