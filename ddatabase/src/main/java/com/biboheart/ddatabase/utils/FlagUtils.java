package com.biboheart.ddatabase.utils;

public class FlagUtils {
    public static String addFlag(String flag, int index) {
        return addFlag(flag, index, 1);
    }

    public static String addFlag(String flag, int index, int val) {
        System.out.println("add flag index:" + index);
        StringBuilder sb = new StringBuilder();
        if (null != flag) {
            sb.append(flag);
        }
        int olen = sb.length();
        for (int i = olen; i < index; i++) {
            sb.append((char)0);
        }
        char ch = sb.charAt(index - 1);
        ch += val;
        sb.setCharAt(index - 1, ch);
        StringBuilder binstr = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            binstr.append(Integer.toBinaryString(sb.charAt(i))).append(" ");
        }
        System.out.println("binstr:" + binstr.toString());
        return sb.toString();
    }

    public static char getChar(String flag, int index) {
        System.out.println("get char index:" + index);
        StringBuilder sb = new StringBuilder();
        if (null != flag) {
            sb.append(flag);
        }
        int olen = sb.length();
        for (int i = olen; i < index; i++) {
            sb.append((char)0);
        }
        return sb.charAt(index - 1);
    }

    public static void setChar(String flag, int index, char ch) {
        System.out.println("set char index:" + index);
        StringBuilder sb = new StringBuilder();
        if (null != flag) {
            sb.append(flag);
        }
        int olen = sb.length();
        for (int i = olen; i < index; i++) {
            sb.append((char)0);
        }
        sb.setCharAt(index - 1, ch);
    }
}
