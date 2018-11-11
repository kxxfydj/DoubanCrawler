package com.kxxfydj.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Slf4j
public enum WorkStatusEnum {
    STUDENT("学生",1),
    WORKER("工作人士",2),
    UNEMPLOYED("待业",3),
    UNKNOW("未知",4);

    WorkStatusEnum(String value, int status) {
        this.value = value;
        this.status = status;
    }

    /**
     * 1 学生 2 工作人士 3 待业 4 未知
     */



    private String value;

    private int status;

    private static final Map<String, Integer> workStatusMap = new HashMap();

    static {
        workStatusMap.put("小学生",STUDENT.getStatus());
        workStatusMap.put("初中生",STUDENT.getStatus());
        workStatusMap.put("高中生",STUDENT.getStatus());
        workStatusMap.put("大学生",STUDENT.getStatus());

        workStatusMap.put("教师",WORKER.getStatus());
        workStatusMap.put("程序员",WORKER.getStatus());
        workStatusMap.put("城镇职工",WORKER.getStatus());
        workStatusMap.put("干部",WORKER.getStatus());

        workStatusMap.put("待业",UNEMPLOYED.getStatus());
        workStatusMap.put("无业游民",UNEMPLOYED.getStatus());

        workStatusMap.put("未知",UNKNOW.getStatus());
    }

    public static int getWorkStatus(String value){
        Integer status = workStatusMap.get(value);
        if(status == null){
            log.warn("没有找到对应的工作状态 value:{}",value);
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
