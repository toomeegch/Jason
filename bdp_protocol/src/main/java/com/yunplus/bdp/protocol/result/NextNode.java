package com.yunplus.bdp.protocol.result;/**
 * @description: 走到下一步的Next
 * @author: liangwen
 * @create: 2019-12-10 16:50
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 走到下一步的Next
 * @author: liangwen
 * @create: 2019-12-10 16:50
 **/
public class NextNode extends  BaseNode {

    public void initNode(StringBuffer word) {
        name=word.toString();

    }


    public Object getValue(Object data) {
             if(data instanceof JSONArray){
                JSONArray resultArray=new JSONArray();
                JSONArray dataarry=(JSONArray)data;
                for(int i=0;i<dataarry.size();i++){
                    Object objecta= dataarry.getJSONObject(i).get(name);
                    resultArray.add(objecta);
                }
                return resultArray;
            }else{
                JSONObject dataJson=(JSONObject)data;
                return  dataJson.get(name);

            }

        }


}
