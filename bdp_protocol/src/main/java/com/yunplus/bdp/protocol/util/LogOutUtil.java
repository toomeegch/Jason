package com.yunplus.bdp.protocol.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LogOutUtil {

    public static void logPrint(String flowName, String flowNum, String errorId,String errorMsg, String topic, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flowName", flowName);//流程名
        jsonObject.put("flowNum", flowNum);//流程步骤（枚举）
        jsonObject.put("errorId", errorId);//错误码
        jsonObject.put("errorMsg", errorMsg);//错误信息
        jsonObject.put("topic", topic);//数据所属kafka topic
        jsonObject.put("data", data);//错误数据
        log.error("bdf_flow_error_data="+String.valueOf(jsonObject));
    }
}
