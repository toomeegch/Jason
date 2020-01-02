package com.yunplus.bdp.protocol.bean;

/**
 * 数据上传协议
 * @author broccoli
 * @since 2019-09-18
 */
public class UploadProtocol extends BaseProtocol {
    /**
     * 数据上报索引
     */
    private String index;
    /**
     * 数据上报内容
     */
    private String data;
    /**
     * 批量上传下页码参数
     */
    private Integer page;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public int getProtocolType() {
        return ProtocolType.UPLOAD;
    }
}
