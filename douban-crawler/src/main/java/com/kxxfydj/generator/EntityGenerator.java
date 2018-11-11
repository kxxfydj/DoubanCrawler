package com.kxxfydj.generator;

import com.alibaba.fastjson.JSONObject;
import com.kxxfydj.anno.DataFormat;
import com.kxxfydj.constant.DataTypeConstant;
import com.kxxfydj.entity.User;
import com.kxxfydj.exception.FormatException;
import com.kxxfydj.formatter.DataFormatter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Slf4j
public class EntityGenerator {

    public static User generatorUser(String jsonBody) {
        return generatorObj(jsonBody, User.class);
    }

    private static <T> T generatorObj(String jsonBody, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            JSONObject jsonObj = JSONObject.parseObject(jsonBody);
            for (String key : jsonObj.keySet()) {
                Class objClazz = User.class;
                Field field = objClazz.getDeclaredField(key);
                String dataType = getDataType(field);
                String methodName = getMethodName(key);
                Method method;
                switch (dataType) {
                    case DataTypeConstant.INT_TYPE:
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{Integer.class});
                        int intValue = DataFormatter.formatIntValue(jsonObj.getString(key), field.getAnnotation(DataFormat.class));
                        method.invoke(obj, intValue);
                        break;
                    case DataTypeConstant.BIGDECIAML_TYPE:
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{BigDecimal.class});
                        BigDecimal bigDecimalValue = DataFormatter.formatBigDecimalValue(jsonObj.getString(key));
                        method.invoke(obj, bigDecimalValue);
                        break;
                    case DataTypeConstant.DATE_TYPE:
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{Date.class});
                        Date dateValue = DataFormatter.formatDateValue(jsonObj.getString(key));
                        method.invoke(obj, dateValue);
                        break;
                    case DataTypeConstant.STRING_TYPE:
                        //TODO
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{String.class});
                        String strValue = DataFormatter.formatStringValue(jsonObj.getString(key));
                        method.invoke(obj, strValue);
                        break;
                    case DataTypeConstant.DOUBLE_TYPE:
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{Double.class});
                        double doubleValue = DataFormatter.formatDoubleValue(jsonObj.getString(key));
                        method.invoke(obj, doubleValue);
                        break;
                    default:
                        method = objClazz.getDeclaredMethod(methodName, new Class<?>[]{field.getType()});
                        method.invoke(obj, getCastValue(field.getType(),jsonObj.getString(key)));
                }

            }
            return obj;
        } catch (Exception e) {
            log.error("生成user对象出错！", e.getMessage(), e);
        }
        return null;
    }

    private static String getMethodName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }

    private static String getDataType(Field field) {
        Annotation annotations = field.getAnnotation(DataFormat.class);
        if (annotations != null) {
            DataFormat dataFormat = (DataFormat) annotations;
            return dataFormat.type();
        }
        return "";
    }

    private static Object getCastValue(Class<?> typeClazz, String value) {
        if (typeClazz == String.class) {
            return value;
        } else if (typeClazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (typeClazz == Double.class) {
            return Double.valueOf(value);
        } else if (typeClazz == Short.class) {
            return Short.valueOf(value);
        } else if (typeClazz == Long.class) {
            return Long.valueOf(value);
        } else {
            throw new FormatException("强制转换出错！");
        }
    }
}
