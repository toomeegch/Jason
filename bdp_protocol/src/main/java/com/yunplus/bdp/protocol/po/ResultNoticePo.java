package com.yunplus.bdp.protocol.po;/**
 * @description: Kafka结果通知
 * @author: liangwen
 * @create: 2019-09-23 17:17
 **/

import lombok.Data;

/**
 * @description: Kafka结果通知
 * @author: liangwen
 * @create: 2019-09-23 17:17
 **/
@Data
public class ResultNoticePo {
    //请求ID
    private String req_id;
    //完成时间
    private String finishTime;


    private String code;

    private String msg;
    //


}
