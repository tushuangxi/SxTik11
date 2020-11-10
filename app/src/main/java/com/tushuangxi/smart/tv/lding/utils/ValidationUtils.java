package com.tushuangxi.smart.tv.lding.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {

        String str = mobile;
        String pattern = "^(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57]|19[89]|166)[0-9]{8}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        return m.matches();
    }
}
