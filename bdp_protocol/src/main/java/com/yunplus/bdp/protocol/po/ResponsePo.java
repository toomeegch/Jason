package com.yunplus.bdp.protocol.po;/**
 * @description: 接口返回结果
 * @author: liangwen
 * @create: 2019-09-24 11:27
 **/

import lombok.Data;

/**
 * @description: 接口返回结果
 * @author: liangwen
 * @create: 2019-09-24 11:27
 **/
@Data
public class ResponsePo {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 协议事件请求Id
     */
    private String req_id;
    private String code="0";



    private String msg="success";

    private Object data;



}
