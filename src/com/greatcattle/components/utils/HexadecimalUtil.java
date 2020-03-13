/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.components.utils;
 
import org.apache.commons.lang.StringUtils;


/**
 * 进制转换
 * */
public class HexadecimalUtil {
    

    /**
     * 十六进制转十进制
     *
     * @param num
     * @return
     */
    public static Integer get10HexNum(String num) {
        if (num.contains("0X")) {
            num = num.replace("0X", "");
        }
        return Integer.parseInt(num.substring(0), 16);
    }

    /**
     * 十进制转十六进制
     *
     * @param num
     * @return
     */
    public static String get16Num(Object num) {

        return Integer.toHexString(Integer.parseInt(num + ""));
    }

    /**
     * 十进制转十六进制,设置长度，不足补0
     *
     * @param num
     * @return
     */
    public static String get16NumAdd0(String num, int len) {
        String str = Integer.toHexString(Integer.parseInt(num)).toUpperCase();
        String res = "";
        if (len >= str.length()) {
            res = StringUtils.repeat("0", (len - str.length())) + str;
        } else {
            return str;
        }
        return res;
    }



    //num & 0xff
    public static int low8(Object num) {
        return Integer.parseInt(num + "") & 0xff;
    }

    //获取高四位
    public static int getHeight4(byte data) {
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
}