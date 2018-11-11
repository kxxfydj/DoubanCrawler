package com.kxxfydj.entity;

import com.kxxfydj.anno.DataFormat;
import com.kxxfydj.constant.DataTypeConstant;
import com.kxxfydj.enums.MarriageStatusEnum;
import com.kxxfydj.enums.WorkStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Data
public class User {
    private String name;
    @DataFormat(type = DataTypeConstant.DATE_TYPE)
    private Date birthday;
    @DataFormat(type = DataTypeConstant.DATE_TYPE)
    private Date workData;
    private Integer age;
    @DataFormat(type = DataTypeConstant.BIGDECIAML_TYPE)
    private BigDecimal money;
    @DataFormat(type = DataTypeConstant.DOUBLE_TYPE)
    private Double salary;
    @DataFormat(type = DataTypeConstant.DOUBLE_TYPE)
    private Double taxRate;
    /**
     * 0 未婚 1 结婚 2 未知
     */
    @DataFormat(type = DataTypeConstant.INT_TYPE, enumClass = MarriageStatusEnum.class)
    private int marriage;
    /**
     * 1 学生 2 工作人士 3 待业 4 未知
     */
    @DataFormat(type = DataTypeConstant.INT_TYPE, enumClass = WorkStatusEnum.class)
    private int workStatus;
}
