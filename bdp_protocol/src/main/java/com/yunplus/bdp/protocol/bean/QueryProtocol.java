package com.yunplus.bdp.protocol.bean;

public class QueryProtocol extends BaseProtocol {

    /**
     * 数据上报索引
     */
    private String index;

    /**
     * 索引内数据键值
     */
    private String[] keys;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String[] getKeys() {
        return this.keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    @Override
    public int getProtocolType() {
        return ProtocolType.QUERY;
    }
}
