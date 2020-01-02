package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * /**
 *
 * @description: 树
 * @author: liangwen
 * @create: 2019-12-10 15:52
 **/
@Slf4j
public class Tree {
    /**
     * 三层Map，第一层请求级别，第二层字段级别，第三层是取值的树结构
     */
      Map<String, Map> requestMap = new HashMap();



    //分词
    public TreeNode division( int state, StringBuffer word) {
        if (state == 1) {//function
               FuntionNode funtionNode = new FuntionNode();
               funtionNode.initNode(word);
               return funtionNode;
        } else if (state == 3) {//数组
            ArrayNode nextNode = new ArrayNode();
            nextNode.initNode(word);
            return nextNode;
        } else if (state == 2) {//
            NextNode nextNode = new NextNode();
            nextNode.initNode(word);
            return nextNode;
        } else {
            throw new RuntimeException("不支持此状态state=" + state);
        }
    }

    public Map<String, TreeNode> createTree(String nameSpace, String columnName,String exp){
        Map<String, Map> columnMap = requestMap.get(nameSpace);

         if(columnMap==null){
            columnMap = new HashMap<String, Map>();
            requestMap.put(nameSpace, columnMap);
        }

        Map nodeMap=columnMap.get(columnName);
        if (nodeMap != null) {
            return nodeMap;
        }
        nodeMap=createTree(exp);

        columnMap.put(columnName,nodeMap);
        return nodeMap;
    }

    public Map  createTree(String exp){
        HashMap treeMap = new HashMap();
        //一个单词
        StringBuffer word = new StringBuffer();
        //1:方法状态，2：向下走的状态
        int state = 2;
        //数组数据
        int state1 = 0;
        TreeNode paramNode=null;
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if ('#' == c&&state==2) {//方法
                state = 1;
                word = new StringBuffer();
                continue;
            }else if ('[' == c&&state!=1) {
                state = 3;
                word.append(c);

            } else if ('.' == c) {

                paramNode= addChild(paramNode,state,word);
                treeMap.put(word.toString(),paramNode);
                word = new StringBuffer();
                state = 2;
                continue;
            } else {
                 word.append(c);
            }
        }
        if(word.length()>0){//叶结点
            paramNode= addChild(paramNode,state,word);
            treeMap.put(word.toString(),paramNode);
            word = new StringBuffer();
        }
        return treeMap;
    }

    /**
     *执行编译的后的语法树
     * @param nodetree
     * @param data
     * @param rootNode
     * @return
     */
    public Object getValue(Map<String, TreeNode> nodetree, Object data,TreeNode rootNode) throws Exception {
        List<TreeNode> dataList= rootNode.getChilds();
        if(dataList.size()<=0){
            Object nodeData=   rootNode.getValue(data);
            return nodeData;
        }
        for(TreeNode node:dataList){
           Object dataObje=   rootNode.getValue(data);;
           return    getValue(nodetree,dataObje,node);
        }
       throw new  RuntimeException("不存在此结点。");
    }


    private TreeNode addChild(TreeNode paramNode,int state, StringBuffer word){
        TreeNode treeNode = division(state,word);
        if(paramNode==null){//为空是首结点
            paramNode=treeNode;
            paramNode.setRoot(true);
        }else{
            paramNode.addChilds(treeNode);
        }
       return treeNode;//做为当前结点

    }


    public TreeNode getRootNode(Map<String, TreeNode>  nodeMap){
        for (Map.Entry<String, TreeNode> entry:nodeMap.entrySet()){
            if(entry.getValue().isRoot()) {
                return entry.getValue();
            }
        }
        throw new  RuntimeException("找不到根结点");
    }

}
