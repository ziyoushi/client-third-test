package com.cc.develop.third.client.util;

/**
 * com.cc.develop.third.client.util
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-02 14:10:59
 */
public class TransCarPlateColor2Code {

    public static String transCarPlateColorToCode(String carPlateColor) {
        switch (carPlateColor) {
            case "蓝牌":
                return "001";
            case "黄牌":
                return "002";
            case "绿牌":
                return "003";
            case "白牌":
                return "004";
            case "其他":
                return "005";
            case "临时车牌":
                return "006";
            case "残疾人车":
                return "010";
            default:
                return "";
        }
    }
}
