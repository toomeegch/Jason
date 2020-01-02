package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 数组的或处理
 * @author: liangwen
 * @create: 2019-12-16 08:52
 **/
public class OrNode extends  BaseNode{
    public void initNode(StringBuffer word) {
        name=word.toString();
    }


    public Object getValue(Object data) throws Exception {
        BaseNode expNodeF = (BaseNode)childs.get(0);
        BaseNode expNodeT = (BaseNode)childs.get(1);
        Boolean value1=(Boolean)expNodeF.getValue(data);
        Boolean value2=(Boolean)expNodeT.getValue(data);
        return  value1||value2?true:false;

    }


}
