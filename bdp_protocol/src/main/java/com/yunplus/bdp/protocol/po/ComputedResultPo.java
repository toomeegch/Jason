package com.yunplus.bdp.protocol.po;/**
 * @description: 计算结果
 * @author: liangwen
 * @create: 2019-09-23 16:13
 **/

import lombok.Data;

/**
 * @description: 计算结果 ,对应的ES里的结果数据
 * @author: liangwen
 * @create: 2019-09-23 16:13
 **/
@Data
public class ComputedResultPo {
    /**
     * 协议事件请求Id
     */
    private String req_id;

    /**
     * 创建时间
     */
    private String create_time;
    //保存的结果串
    private String rsult;



}
