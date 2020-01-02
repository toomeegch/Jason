package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 数据结点
 * @author: liangwen
 * @create: 2019-12-10 16:54
 **/
@Slf4j
public class ArrayNode extends BaseNode {
    //参数  ，Key:参数名，Value：树的根结点
    private Map<String, TreeNode> treeMap = new HashMap<String, TreeNode>();
    List<BaseNode> nodeList = new ArrayList();

    public void initNode(StringBuffer word) {
        StringBuffer name = new StringBuffer();
        StringBuffer wordTemp = new StringBuffer();
        BaseNode orAndNode = null;
        BaseNode firstExpNode = null;
        //1:
        int state = 0;//一级状态
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
             if (c == '[') {
                state++;

            } else if (c == ']') {
                state--;
            } else if (state == 1) {//语句
                //分词
                if (c == ' ') {
                    if (word.charAt(i + 1) == ' ') {
                        continue;
                    }
                    if (wordTemp.toString().equals("and")) {
                        orAndNode = new AndNode();
                        orAndNode.initNode(wordTemp);
                        nodeList.add(orAndNode);
                    } else if (wordTemp.toString().equals("or")) {
                        orAndNode = new OrNode();
                        orAndNode.initNode(wordTemp);
                        nodeList.add(orAndNode);
                        //表达式
                    } else {
                        firstExpNode = getExpressionNode(wordTemp, orAndNode, firstExpNode);
                    }
                     wordTemp = new StringBuffer();
                 } else {
                    wordTemp.append(c);
                }

            } else if (state == 0) {
                name.append(c);
            }

        }
        if (state != 0) {
            throw new RuntimeException("查询表达式在[]之中");
        }
        //结束处理
        if (wordTemp.length() > 0) {//最后面的结表式
            firstExpNode = getExpressionNode(wordTemp, orAndNode, firstExpNode);
        }
        //
        this.name = name.toString();
    }


    public Object getValue(Object data) throws Exception {
         JSONObject dataJson = (JSONObject) data;
        JSONArray jsonObject = dataJson.getJSONArray(name);
        int size = jsonObject.size();
        JSONArray jsonOb = new JSONArray();
        //循环数据
        for(int i=0;i<size;i++) {
            JSONObject o = (JSONObject) jsonObject.get(i);
            Boolean nextRsult = null;
            //循环条件
            for (BaseNode baseNode : nodeList) {
                String key = baseNode.getKey();
                 if (nextRsult != null) {
                    if (key.equals("or") && nextRsult) {
                        break;
                    }
                    CostNode costNode = (CostNode) baseNode.getChilds().get(0);
                    costNode.setValue(nextRsult);
                }
                nextRsult = (Boolean) baseNode.getValue(o);
                
              }
            //输出结果数据
            if (nextRsult) {
                 jsonOb.add(jsonObject.get(i));
            }
             }

           return jsonOb;
    }

    private BaseNode getExpressionNode(
            StringBuffer wordTemp,
            BaseNode orAndNode,
            BaseNode firstExpNode) {
        BaseNode expNode = new ExpressionNode();
        expNode.initNode(wordTemp);
        if (orAndNode == null) {
            firstExpNode = expNode;
            return firstExpNode;
        } else {
            if (firstExpNode != null) {
                orAndNode.addChilds(firstExpNode);
            }else{
                CostNode costNode = new CostNode();//增加一个空的结点，主要用来占位，以方便放前面的操行结果
                orAndNode.addChilds(costNode);
            }
            orAndNode.addChilds(expNode);
            return null;
        }
    }


}
