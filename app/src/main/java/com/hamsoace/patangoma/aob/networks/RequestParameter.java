package com.hamsoace.patangoma.aob.networks;

import java.util.HashMap;
import java.util.Map;

public class RequestParameter {
    private Map<String, String> requestParam;

    public RequestParameter(){
        this.requestParam = new HashMap<>();
    }

    public void put(String key, String value){
        requestParam.put(key, value);
    }
}
