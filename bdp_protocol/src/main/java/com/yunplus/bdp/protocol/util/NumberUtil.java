package com.yunplus.bdp.protocol.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static Boolean compare(Object a ,Object b){
        //判断a是否大于b
        if(NumberUtil.isNumeric(a.toString())&&NumberUtil.isNumeric(b.toString())) {
            if(Double.parseDouble(a.toString())-Double.parseDouble(b.toString())>=0){
                return true;
            }else{return false;}
        }else{
            throw new IllegalArgumentException("参数异常");
        }
    }
    public void json(){
    }

}
