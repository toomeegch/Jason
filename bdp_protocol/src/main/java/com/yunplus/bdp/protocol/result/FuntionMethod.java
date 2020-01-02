package com.yunplus.bdp.protocol.result;
import com.yunplus.bdp.protocol.util.NumberUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuntionMethod {
    //case when功能
    public Object match(Object le,Object ri,Object param,Object result1,Object result2){
        if(Double.parseDouble(param.toString())<Double.parseDouble(le.toString())||Double.parseDouble(param.toString())>Double.parseDouble(ri.toString())){
            return result1;
        }else{
            return result2;
        }
    }
    //min功能
    public  Object min(Object pa1,Object pa2){
        //如果为数字比较数字的值
        Boolean compare = NumberUtil.compare(pa1, pa2);
        if(compare){
            return pa1;
        }else{return  pa2;}
    }

    //max功能
    public Object max(Object pa1,Object pa2){
        //如果为数字比较数字的值
        Boolean compare = NumberUtil.compare(pa1, pa2);
        if(compare){
            return pa2;
        }else{return  pa1;}
    }

    //字符串转日期转换
    public Object StrSwitchDate(String pa,String format) throws ParseException {
        //判断需要格式化的类型
        Date parse;
        SimpleDateFormat df = new SimpleDateFormat(format);
        //判断是时间戳还是普通时间字符串
        if(pa.charAt(0)=='1'){
           parse= df.parse(pa);
        }else{
            parse=df.parse(pa);
        }
        return parse;
    }

    //日期转字符串
    public Object DateSwitchStr(Object date,String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        if(NumberUtil.isNumeric(date.toString())){
            return df.format(Long.getLong(date.toString()));
        }
        Date date1 = (Date) date;

        return df.format(date1);
    }

    //去掉空格
    public Object trim(Object data){
        return data.toString().trim();
    }
    //字符串替换
    public Object replace(Object  ob,Object a ,Object b){
        return  ob.toString().replaceAll(a.toString(),b.toString());
    }
    //null值处理
    public Object ifNull(Object param,Object param2){
        String s = param.toString();
        if(s.equals("NULL")||s.equals("null")){
            return param2;
        }
        return param;
    }
    //保留小数点几位,四舍五入
    public Object precision(Object param,Object param2){
        BigDecimal bg = new BigDecimal(Double.parseDouble(param.toString()));
        double v = bg.setScale(Integer.parseInt(param2.toString()), BigDecimal.ROUND_HALF_UP).doubleValue();
        return v;
    }
    //返回当前日期
    public Object now(){
        return new Date();
    }


}
