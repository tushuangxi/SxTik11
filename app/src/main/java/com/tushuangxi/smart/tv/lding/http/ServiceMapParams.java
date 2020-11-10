package com.tushuangxi.smart.tv.lding.http;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 请求参数
 */
public class ServiceMapParams {


    //GetTokenNoLoginRsp
    public static Map<String, Long> getGetTokenNoLoginRspMapParams(Long sign){
        Map<String, Long> map = new HashMap<>();
        map.put("sign",sign);
        return map;
    };


}
