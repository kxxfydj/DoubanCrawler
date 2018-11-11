package com.kxxfydj.formatter;

import com.google.common.base.Joiner;
import com.kxxfydj.anno.DataFormat;
import com.kxxfydj.enums.MarriageStatusEnum;
import com.kxxfydj.enums.WorkStatusEnum;
import com.kxxfydj.exception.FormatException;
import com.kxxfydj.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Slf4j
public class DataFormatter {

    public static int formatIntValue(String intValueStr, DataFormat enumObj) {
        intValueStr = omitAnyBlankSpace(intValueStr);
        Class clazz = enumObj.enumClass();
        if (clazz == MarriageStatusEnum.class) {
            return MarriageStatusEnum.getMarriageStatus(intValueStr);
        } else if (clazz == MarriageStatusEnum.class) {
            return WorkStatusEnum.getWorkStatus(intValueStr);
        }
        log.error("格式化没有找到对应的值！");
        throw new FormatException("格式化没有找到对应的值");
    }

    public static String formatStringValue(String stringValue) {
        //TODO
        return stringValue;
    }

    public static Date formatDateValue(String dateValueStr) {
        dateValueStr = omitAnyBlankSpace(dateValueStr);
        List<String> dateStr = RegexUtil.multiGroups(dateValueStr, ".*?(\\d{2,4}).*?(\\d{1,2}).*?(\\d{1,2}).*?", 1, 2, 3);
        try {
            Joiner joiner = Joiner.on("-");
            String result = joiner.join(dateStr);
            return DateUtils.parseDate(result, "YYYY-MM-dd");
        } catch (Exception e) {
            log.error("格式化日期出错！", e.getMessage(), e);
        }
        return null;
    }

    public static BigDecimal formatBigDecimalValue(String bigDecimalValueStr) {
        double formatDouble = formatDoubleValue(bigDecimalValueStr);
        return BigDecimal.valueOf(formatDouble);
    }

    public static double formatDoubleValue(String doubleValueStr) {
        doubleValueStr = omitAnyBlankSpace(doubleValueStr);
        String omitComma = doubleValueStr.replaceAll(",", "");
        String cleanedValue = RegexUtil.singleGroup(omitComma, ".*?(\\d+(\\.?\\d+)).*?", 1);
        if (StringUtils.isBlank(cleanedValue)) {
            throw new FormatException("格式化double出错！");
        }
        if(omitComma.contains("%")){
            return Double.valueOf(cleanedValue) / 100;
        }
        return Double.valueOf(cleanedValue);
    }

    private static String omitAnyBlankSpace(String origin) {
        return origin.trim().replaceAll(" ", "");
    }
}
