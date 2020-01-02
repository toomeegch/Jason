package com.yunplus.bdp.protocol.bean;
/**
 * 任务计算协议
 * @author Broccoli
 * @since 2019-09-18
 */
public class TaskProtocol extends BaseProtocol {
    private String[] taskName;

    public void setTaskName(String[] taskName) {
        this.taskName = taskName;
    }

    public String[] getTaskName () {
        return this.taskName;
    }

    @Override
    public int getProtocolType() {
        return ProtocolType.TASK;
    }
}
