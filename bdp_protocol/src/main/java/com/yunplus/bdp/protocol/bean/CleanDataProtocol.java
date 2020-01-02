package com.yunplus.bdp.protocol.bean;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @description: 数据清洗，保存到大数据
 * @author: liangwen
 * @create: 2019-10-23 11:23
 **/
@Data
public class CleanDataProtocol {
    /**
     * Kafka的主题
     */
    String   topic;
    //需要清洗的数据
    JSONObject data;
    //保存在ES的
    String  indexName;

}
