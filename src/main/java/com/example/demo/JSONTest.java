package com.example.demo;

import com.alibaba.fastjson.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONTest {

    public static void main(String[] args) {
        String ret;
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        stringList.add("ddd");
//list-->json
        String jsonString = JSON.toJSONString(stringList);
//json-->list
        List<String> list =JSON.parseArray(jsonString,String.class);

        Map<String,Integer> map = new HashMap<>();
        map.put("zhangsan", 18);
        map.put("lisi", 28);
        map.put("wangwu", 38);
//map-->json
        String jsonString2 = JSON.toJSONString(map);
//json-->map
        Map<String,Integer> map2 =JSON.parseObject(jsonString2,Map.class);

        ret = getValueByKeyFromJson(jsonString2, "zhangsan");
        System.out.println(ret);
    }

    public static String getValueByKeyFromJson(Object object, String key) {
        if (object == null || object == "")
            return null;
        Object oJson = object;
        Class<? extends Object> cls = oJson.getClass();
        if (cls == JSONObject.class) {
            JSONObject jo = (JSONObject) oJson;
            if (jo.containsKey(key)) {
                return jo.getString(key);
            }
            for (Object o : jo.values()) {

                String tmp = getValueByKeyFromJson(o, key);
                if (!tmp.equals("Not Found") && tmp != null) {
                    return tmp;
                }
            }
//        } else if (cls == JSONArray.class) {
//
//            JSONArray ja = (JSONArray) oJson;
//            int size = ja.size();
//            for (int i = 0; i < size; i++) {
//                Object o = ja.get(i);
//                if (o != null && o != "") {
//                    String tmp = getValueByKeyFromJson(o, key);
//                    if (!tmp.equals("Not Found") && tmp != null) {
//                        return tmp;
//                    }
//                }
//            }
        } else if (cls == String.class) {

            Object o = null;
            try {
                o = JSON.parse((String) oJson);
                String tmp = getValueByKeyFromJson(o, key);
                if (!tmp.equals("Not Found") && tmp != null) {
                    return tmp;
                }
            } catch (JSONException e) {
            }
        }
        return "Not Found";
    }

}
