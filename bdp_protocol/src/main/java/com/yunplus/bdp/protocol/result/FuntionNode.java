package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liangwen
 * @create: 2019-12-10 15:53
 **/
public class FuntionNode extends BaseNode {
    Tree tree = new Tree();

    //参数  ，Key:参数名，Value：树的根结点
    private List param=new ArrayList();
    /**
     *
     */
    String methodName;


    public void initNode(StringBuffer word){

        int statue=0;//括号
        StringBuffer paramstr=new StringBuffer();
        //检测试
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if('('==c){
                statue++;
            }else if(')'==c){
                statue--;
            }
        }
        if(statue!=0){
            throw new RuntimeException("函数语法错误："+word);
        }

        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if('('==c ){
                statue++;
                if(statue==1){
                    methodName=paramstr.toString();
                    paramstr=new StringBuffer();
                    continue;
                }
            }else if(','==c){
                if(statue==1){
                    handle(paramstr.toString());
                    paramstr=new StringBuffer();
                    continue;
                }
            }else if(')'==c){
                if(statue==1){
                    handle(paramstr.toString());
                    paramstr=new StringBuffer();
                    continue;
                }
                statue--;
            }
            paramstr.append(c);
        }

    }






    public Object getValue(Object data) throws Exception {
        FuntionInvoke funtionInvoke = FuntionInvoke.getFuntionInvoke();
        List paramValue = new ArrayList();
        for (Object pa : param) {
            if (pa instanceof TreeNode) {
                Object value = ((TreeNode) pa).getValue(data);
                paramValue.add(value);
            } else {
                paramValue.add(pa);//常量
            }
        }
        return funtionInvoke.invode(methodName, paramValue);
    }

        private void handle(String paramStr){
            if(paramStr.equals("")){
                throw new RuntimeException("参数值不能为空");
            }
            if(paramStr.indexOf('\'')==0) {//常量
                if(paramStr.lastIndexOf('\'')==paramStr.length()-1){
                    paramStr= paramStr.substring(1,paramStr.lastIndexOf('\''));
                    param.add(paramStr);
                    return ;
                }else {
                    throw new RuntimeException("常量要在双引号之中");
                }
            }
            Map nodeMap=tree.createTree(paramStr);
            param.add(  tree.getRootNode(nodeMap)) ;


        }

    }
