package com.cc.develop.third.client.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.cc.develop.third.client.util
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-02 14:10:27
 */
public class RegexUtil {
    public static boolean isLegalDate(String sDate) {
        int legalLen = 10;
        int length = 8;
        int lastLength = 9;
        if (sDate.length() == length) {
            DateFormat formatter = new SimpleDateFormat("yyyy/M/d");
            try {
                Date date = formatter.parse(sDate);
                return sDate.equals(formatter.format(date));
            } catch (Exception e) {
                return false;
            }
        }

        if (sDate.length() == lastLength) {
            return checkStrDateBy9Length(sDate);
        }

        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }

    }

    private static boolean checkStrDateBy9Length(String strDate) {
        //根据/分割
        String[] split = strDate.split("/");
        String year = split[0];
        String month = split[1];
        String day = split[2];
        if (year.length() > 4 || year.length() < 4) {
            return false;
        }
        if (month.length() > 2 || month.length() < 1) {
            return false;
        }
        if (day.length() > 3 || day.length() < 1) {
            return false;
        }
        StringBuffer format = new StringBuffer("yyyy/");
        switch (month.length()) {
            case 1:
                format.append("M");
                format.append("/");
                break;
            case 2:
                format.append("MM");
                format.append("/");
                break;
            default:
                break;
        }
        switch (day.length()) {
            case 1:
                format.append("d");
                break;
            case 2:
                format.append("dd");
                break;
            default:
                break;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format.toString());

        try {
            Date date = sdf.parse(strDate);
            return strDate.equals(sdf.format(date));
        } catch (Exception e) {
            return false;
        }
    }
}
