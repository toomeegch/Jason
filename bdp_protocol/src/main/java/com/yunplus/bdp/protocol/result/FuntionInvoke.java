package com.yunplus.bdp.protocol.result;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @description:  方法调用管理
 * @author: liangwen
 * @create: 2019-12-17 15:11
 **/
public class FuntionInvoke {
  static  FuntionInvoke funtionInvoke = new FuntionInvoke();
    Map<String,Method>   methodMap = new HashMap();
    Map<String,Object>   objectMap = new HashMap();

    public static FuntionInvoke getFuntionInvoke() {
        return funtionInvoke;
    }
    private FuntionInvoke (){}

    public void initMethod(Class cla) throws IllegalAccessException, InstantiationException {
        Method[] methods = cla.getDeclaredMethods();
        Object obj=cla.newInstance();
        for(Method m:methods){
            methodMap.put(m.getName(),m);
            objectMap.put(m.getName(),obj);
        }

    }

    public Object invode(String name, List param) throws Exception{
          Method m= methodMap.get(name);
          Object obj= objectMap.get(name);

          return m.invoke(obj,param.toArray());
    }

}
