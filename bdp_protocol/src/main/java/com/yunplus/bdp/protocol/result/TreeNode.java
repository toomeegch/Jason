package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**

/**
 * @description: 树结点
 * @author: liangwen
 * @create: 2019-12-10 15:51
 **/

public interface TreeNode {


public void initNode(StringBuffer word);
    public void addChilds(TreeNode node);
    public  List<TreeNode> getChilds();

    public  boolean isRoot();

    public  String getKey();

    public  void setRoot( boolean roote);

    public  Object getValue( Object data) throws Exception;



}
