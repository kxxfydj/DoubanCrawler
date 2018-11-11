package com.kxxfydj.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Slf4j
public enum MarriageStatusEnum {
    MARRIAGED("已婚",0),
    UNMARRIAGED("未婚",1),
    UNKNOW("未知",2);

    private String value;

    private int status;

    private static final Map<String, Integer> statusMap = new HashMap();

    static {
        statusMap.put("未婚",UNMARRIAGED.getStatus());
        statusMap.put("单身",UNMARRIAGED.getStatus());

        statusMap.put("已婚",MARRIAGED.getStatus());
        statusMap.put("结婚",MARRIAGED.getStatus());

        statusMap.put("未知",UNKNOW.getStatus());
        statusMap.put("未显示",UNKNOW.getStatus());
    }


    MarriageStatusEnum(String value, int status) {
        this.value = value;
        this.status = status;
    }

    public static int getMarriageStatus(String value){
        Integer status = statusMap.get(value);
        if(status == null){
            log.warn("没有找到对应的婚姻状况 value:{}",value);
        }
        return status;
    }

    public String getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }
}
