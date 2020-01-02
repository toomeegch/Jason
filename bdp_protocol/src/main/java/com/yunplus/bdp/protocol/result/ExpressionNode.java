package com.yunplus.bdp.protocol.result;
import com.yunplus.bdp.protocol.util.NumberUtil;
import java.util.Date;

/**
 * @description: 数组过滤表达式
 * @author: liangwen
 * @create: 2019-12-16 10:09
 **/
public class ExpressionNode extends  BaseNode {
    Object  lexp;
    Object  rexp;
    int opt;//1:> 2:>= 3:< 4:<= 5:= 6:!=
    int type;//1;数字，2：时间，3：字符串 0：未知
    public void initNode(StringBuffer word) {
         name=word.toString();
         int state=0;
        StringBuffer wordTemp=new StringBuffer();
         for(int i=0;i<word.length();i++){
             char c=word.charAt(i);
             if('#'==c){
                 state=1;
             }else if('>'==c){
                 if(word.charAt(i+1)=='='){
                     i++;
                     opt=2;
                  }else{
                     opt=1;
                 }
                 lexp= getExp(state,wordTemp);
                 wordTemp=new StringBuffer();
                 state=0;
             }else if('<'==c){
                 if(word.charAt(i+1)=='='){
                     i++;
                     opt=4;
                 }else{
                     opt=3;
                 }
                 lexp= getExp(state,wordTemp);
                 wordTemp=new StringBuffer();
                 state=0;
             }else if('='==c){
                 if(word.charAt(i+1)=='='){
                     i++;
                     opt=5;
                     lexp= getExp(state,wordTemp);
                 }else{
                     throw  new RuntimeException("相等表达式不正确，"+word.toString());
                 }
                 wordTemp=new StringBuffer();
                 state=0;
             }else if('!'==c){
                 if(word.charAt(i+1)=='='){
                     i++;
                     opt=6;
                     lexp= getExp(state,wordTemp);
                 }else{
                     throw  new RuntimeException("不相等表达式不正确，"+word.toString());
                 }
                 wordTemp=new StringBuffer();
                 state=0;
             }else{
                 wordTemp.append(c);
             }
         }
        rexp= getExp(state,wordTemp);
        wordTemp=new StringBuffer();
        state=0;
    }

    public Object getValue(Object data)throws  Exception {
          Object lvalue=   getNodeValue(lexp,data);
          Object rvalue=   getNodeValue(rexp,data);
         type = getType(lvalue);
        switch (type){//1;数字，2：时间，3：字符串 0：未知
            case 1: return handleByNumber(lvalue,rvalue);
            case 2: return handleByDate(lvalue,rvalue);
            case 3: return handleByStr(lvalue,rvalue);
            default: throw new Exception("数据异常 type="+type);
        }

    }

    private Object handleByStr(Object lvalue, Object rvalue) {
        //字符串处理数据
        String ls = (String) lvalue;
        String rs = (String) rvalue;
        switch (opt){
            case 5:return ls.equals(rs)? true:false;
            default:throw new RuntimeException("操作不存在 type="+type);
        }
    }

    private int getType(Object lvalue){
        if(NumberUtil.isNumeric(lvalue.toString())){
            return 1;
        }
        if(type>0){
            return type;
        }
        if(lvalue instanceof Date){
            return 2;
        }
        if(lvalue instanceof String){
            return 3;
        }

        throw new RuntimeException("表达式的值类型错误，value="+lvalue);
    }
    private Object handleByDate(Object lvalue,Object rvalue) {
        //判断时间大小

        Date ldate = (Date) lvalue;
        Date rdate = (Date) rvalue;
        long rt = rdate.getTime();
        long lt = ldate.getTime();
        switch (opt){
            case 1:return   lt>rt? true:false;
            case 2:return   lt>=rt? true:false;
            case 3:return   lt<rt? true:false;
            case 4:return   lt<=rt? true:false;
            case 5:return   lt==rt? true:false;
            case 6:return   lt!=rt? true:false;
            default: throw new RuntimeException("操作不存在 type="+type);

        }

    }
    private Object handleByNumber(Object lvalue,Object rvalue){

        Double rv=Double.valueOf(rvalue.toString());
        Double lv=Double.valueOf(lvalue.toString());
        switch (opt){
            case 1:return   lv>rv? true:false;
            case 2:return   lv>=rv? true:false;
            case 3:return   lv<rv? true:false;
            case 4:return   lv<=rv? true:false;
            case 5:return   lv.equals(rv)? true:false;
            case 6:return   lv!=rv? true:false;
            default: throw new RuntimeException("操作不存在 type="+type);

        }
    }



   private Object getNodeValue(Object nodeOrValue,Object data)throws  Exception{
        if (nodeOrValue instanceof  TreeNode){
           return  ((TreeNode) nodeOrValue).getValue(data);
        }else{
            return nodeOrValue;
        }
    }

   private  Object getExp(int state,StringBuffer wordTemp){
       if(state==1){
           FuntionNode  node=new FuntionNode();
           node.initNode(wordTemp);
           lexp=node;
           state=0;
           return node;
       }
       if(NumberUtil.isNumeric(wordTemp.toString())){
           Double  dvalue = Double.parseDouble(wordTemp.toString());
           type=1;
           return dvalue;
       }

       NextNode nextNode = new NextNode();
       nextNode.initNode(wordTemp);
       return nextNode;
   }
}
