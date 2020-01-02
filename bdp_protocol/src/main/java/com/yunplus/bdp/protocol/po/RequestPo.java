package com.yunplus.bdp.protocol.po;/**
 * @description: 接口请求的BO
 * @author: liangwen
 * @create: 2019-09-24 11:29
 **/

import lombok.Data;

/**
 * @description: 接口请求的BO
 * @author: liangwen
 * @create: 2019-09-24 11:29
 **/
@Data
public class RequestPo {
    /**
     * 业务类型名，由业务和平定约定好
     */
    String type_name;
    /**
     * 协议事件请求Id
     */
    private String req_id;


    //
    private Object data;

}
