package com.kxxfydj.main;

import com.kxxfydj.entity.User;
import com.kxxfydj.generator.EntityGenerator;

/**
 * Created by kxxfydj on 2018/11/11.
 */
public class Main {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"name\": \"BeJson\",\n" +
                "    \"birthday\": \"1996/06/09\",\n" +
                "    \"workData\": \"2003-05-06\",\n" +
                "    \"age\": \"35\",\n" +
                "    \"money\": \"355434.32\",\n" +
                "    \"salary\": \"7838.64\",\n" +
                "    \"taxRate\": \"40%\"\n" +
                "}";
        User user = EntityGenerator.generatorUser(json);
        System.out.println();

//        String ss = "1996/09/03";
//        System.out.println(RegexUtil.singleGroup(ss,".*?(\\d{2,4}).*?(\\d{1,2}).*?(\\d{1,2}).*?",2));
    }
}
