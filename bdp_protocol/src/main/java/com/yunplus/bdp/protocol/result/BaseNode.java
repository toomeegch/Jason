package com.yunplus.bdp.protocol.result;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 结点的基本类
 * @author: liangwen
 * @create: 2019-12-16 09:52
 **/
public abstract  class BaseNode implements TreeNode{
    String name;
    boolean isRoot=false;//是否根接点
    //子结点
    List<TreeNode> childs=new ArrayList<TreeNode>();


    public  boolean isRoot(){
        return isRoot;
    }
    public  void setRoot( boolean roote){
        isRoot=roote;
    }
    public  String getKey(){
        return name;
    }

    public void addChilds(TreeNode node){
        childs.add(node);
    }
    public  List<TreeNode> getChilds(){
        return  childs;
    }



}
