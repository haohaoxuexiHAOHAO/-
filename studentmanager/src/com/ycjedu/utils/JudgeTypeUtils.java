package com.ycjedu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeTypeUtils {
    private static String judge_exp="^\\d+$";
    private static Pattern  pattern=Pattern.compile(judge_exp);

    public static boolean JudgeStringIsInteger(String target){
        if(target==null||target.equals(""))return false;
        Matcher matcher = pattern.matcher(target);
        if(matcher.find())return true;
        else return false;
    }

}
