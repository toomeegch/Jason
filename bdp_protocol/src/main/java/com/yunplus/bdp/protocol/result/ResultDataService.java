package com.yunplus.bdp.protocol.result;

import com.alibaba.fastjson.JSONObject;
import com.yunplus.bdp.protocol.util.NumberUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 结果数据服务
 * @author: liangwen
 * @create: 2019-12-10 17:33
 **/
public class ResultDataService {
    Tree tree = new Tree();

    static ResultDataService resultDataService = new ResultDataService();

    public static ResultDataService getInstance() {
        return resultDataService;
    }

    private ResultDataService() {
    }


    /**

     *
     * @param namespace
     * @param jsonstr
     * @param data
     * @return

     */
    public Map getValue(String namespace,String jsonstr,JSONObject data) throws Exception {
        Map resulwMap = new HashMap();
        JSONObject  json = JSONObject.parseObject(jsonstr);
        for(Object key:json.keySet()){
            String keyStr=(String) key;
            Map<String, TreeNode>  nodeMap=   tree.createTree(namespace,keyStr,json.getString(keyStr));
            TreeNode rootNode= tree.getRootNode(nodeMap);
            Object resultValue= tree.getValue(nodeMap,data,rootNode);
            resulwMap.put(key,resultValue);
        }
      return resulwMap;
    }





    public static void main(String[] arg)throws  Exception{
        test2();
    }
    private static void  test1() throws Exception {
        String str="{\"r\":\"a.b.\",\"r1\":\"f.e\"}";
        JSONObject dataObj= JSONObject.parseObject("{\"a\":{\"b\":\"bv\"},\"f\":{\"e\":\"ev\"}}");
        ResultDataService resultDataService = ResultDataService.getInstance( );
        Map data= resultDataService.getValue("space",str,dataObj);
        System.out.println(data);
    }
    private static void  test2() throws Exception {
        String str="{\"apply_time\":\"hits.hits[_score>0 and _score>0]._score\"}";
        JSONObject dataObj= JSONObject.parseObject("{\"took\":0,\"timed_out\":false,\"_shards\":{\"total\":2,\"successful\":2,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":{\"value\":1,\"relation\":\"eq\"},\"max_score\":1.89712,\"hits\":[{\"_index\":\"bdp.tag_applyloan201901\",\"_type\":\"_doc\",\"_id\":\"applyloan20190118150237148\",\"_score\":1.89712,\"_source\":{\"apply_loan\":{\"loan_info\":[{\"loan_cycle_length\":1,\"loan_cycle_num\":1,\"loan_type\":1}],\"create_time\":1547794957000,\"business_info\":{\"assets_id\":16,\"namespace\":\"1001\"},\"loan_id\":null},\"entity_to_id\":\"20190118150237148\",\"update_time\":\"2019-12-17 20:41:36\",\"relate\":{\"loan_apply_time\":1547794957000},\"data_type\":\"applyloan\",\"entity_from_id\":\"200014851\",\"user\":{\"sex\":10,\"provin\":\"安徽省\",\"nanme\":\"沈显乐\",\"age\":29,\"big_log_data*\":[]}}}]}}\n");
        ResultDataService resultDataService = ResultDataService.getInstance( );
        Map data= resultDataService.getValue("space",str,dataObj);
        System.out.println(data);
    }
    private static void  test3() throws Exception {
        FuntionInvoke.getFuntionInvoke().initMethod(FuntionMethod.class);
        String str="{\"r\":\"a.b.\",\"r1\":\"f.e[repay_time>0   and  #min('6000','4000')<#match('10','200',repay_time,'100','1000') or repay_amt==1000].repay_time\"}";
        JSONObject dataObj= JSONObject.parseObject("{\"a\":{\"b\":\"bv\"},\"f\":{\"e\":[{\"repay_time\":1543298400000,,\"repay_amt\":1000},{\"repay_time\":15,\"repay_amt\":10}]}}");
        ResultDataService resultDataService = ResultDataService.getInstance( );
        Map data= resultDataService.getValue("space",str,dataObj);
        System.out.println(data);
    }
}
