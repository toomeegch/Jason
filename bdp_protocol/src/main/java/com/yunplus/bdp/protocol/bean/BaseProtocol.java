package com.yunplus.bdp.protocol.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 基础协议
 * @author Broccoli
 * @since 2019-09-18
 */
public abstract class BaseProtocol {
    /**
     * 协议事件请求Id
     */
    @JsonAlias("req_id")
    private String reqId;

    /**
     * 业务唯一标识Id
     */
    @JsonAlias("biz_id")
    private String bizId;

    /**
     * 请求时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /**
     * 任务超时时间
     */
    @JsonAlias("expire_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    public abstract int getProtocolType();

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
