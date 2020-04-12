package com.example.demo;

import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("test")
public class test_d {
    @RequestMapping(value = "get",method = RequestMethod.GET)
    String get() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //assert addr != null;
        String ip= null; //获取本机ip
        if (addr != null) {
            ip = addr.getHostAddress().toString();
        }
        return "Host ip is " +  ip;
    }


    @RequestMapping(value = "post", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String post(@RequestBody String params){

        String ret = params;

        return "Hello from post" + ret;
    }
    @RequestMapping(value = "put",method = RequestMethod.PUT)
    String put(){
        return "Hello from put";
    }
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    String delete(){
        return "Hello from delete";
    }

    public String getValueByKeyFromJson(Object object, String key) {
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



