package com.yunplus.bdp.protocol.result;

/**
 * @description:
 * @author: liangwen
 * @create: 2019-12-18 17:25
 **/
public class CostNode<T> extends  BaseNode {
    T data;

    public void initNode(StringBuffer word) {

    }
    public void setValue(T data){
        this.data=data;
    }

    public T getValue(Object data) throws Exception {
        return this.data;
    }
}