package com.example.mydream_back.utils;

public class StringHelper {

    /**
     * 判断字符串是否为空（null、空字符串或纯空白）
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 安全地获取字符串（防止 null 报错）
     */
    public static String safeString(String str) {
        return safeString(str, "");
    }

    /**
     * 安全地获取字符串，并指定默认值
     */
    public static String safeString(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * 去除字符串两端的空白字符
     */
    public static String trim(String str) {
        return safeString(str).trim();
    }

    /**
     * 获取字符串长度（安全处理）
     */
    public static int length(String str) {
        return safeString(str).length();
    }

    /**
     * 格式化字符串（替换占位符，如 format("Hello {0}", "Tom") => Hello Tom）
     */
    public static String format(String template, Object... args) {
        if (template == null || args == null || args.length == 0) {
            return safeString(template);
        }

        StringBuilder sb = new StringBuilder(template);
        for (int i = 0; i < args.length; i++) {
            String placeholder = "{" + i + "}";
            int index = sb.indexOf(placeholder);
            while (index != -1) {
                sb.replace(index, index + placeholder.length(), args[i].toString());
                index = sb.indexOf(placeholder, index + args[i].toString().length());
            }
        }
        return sb.toString();
    }

    /**
     * 截断字符串并在末尾加上省略号（...）
     */
    public static String truncate(String str, int maxLength) {
        return truncate(str, maxLength, "...");
    }

    public static String truncate(String str, int maxLength, String suffix) {
        String input = safeString(str);
        if (input.length() <= maxLength) {
            return input;
        }
        return input.substring(0, maxLength) + suffix;
    }

    /**
     * 判断字符串是否以指定子串结尾
     */
    public static boolean endsWith(String str, String suffix) {
        return safeString(str).endsWith(suffix);
    }

    /**
     * 判断字符串是否包含指定子串
     */
    public static boolean contains(String str, String substr) {
        return safeString(str).contains(substr);
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 将字符串首字母大写
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) return str;
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 驼峰命名转下划线命名（如 camelCase -> camel_case）
     */
    public static String toUnderlineCase(String str) {
        if (isEmpty(str)) return str;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}