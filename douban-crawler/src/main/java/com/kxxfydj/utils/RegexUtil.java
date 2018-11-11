package com.kxxfydj.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kxxfydj on 2018/11/11.
 */
@Slf4j
public class RegexUtil {

    public static List<String> multiGroups(String content, String regex, int... groups) {
        List<String> resultList = new ArrayList();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.groupCount() == groups.length) {
            for (int group : groups) {
                resultList.add(matcher.group(group));
                matcher.reset();
            }
        } else {
            log.warn("正则表达式无法正确匹配！content:{}, regex:{}",content, regex);
        }

        return resultList;
    }

    public static String singleGroup(String content, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }
}
