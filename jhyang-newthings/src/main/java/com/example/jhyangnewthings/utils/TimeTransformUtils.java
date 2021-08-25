package com.example.jhyangnewthings.utils;

import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import static com.example.jhyangnewthings.common.constant.NumConstant.IntNum.*;
import static com.example.jhyangnewthings.common.constant.Symbol.*;
public class TimeTransformUtils {

    /**
     * long型秒数转为 yyyy-MM-dd HH:mm:ss
     *
     * @param srcVal
     * @return
     */
    public static String dateTimeTransform(String srcVal) {
        if (StringUtils.isNotEmpty(srcVal)) {
            if (srcVal.length() == NUM_10) {
                return convertTimeToString(Long.parseLong(srcVal));
            } else if ((srcVal.length() == NUM_12)) {
                String time = getShowTime(srcVal + "00");
                return Integer.valueOf(time.substring(0, NUM_4)) > Integer.valueOf(getThisYear()) ? EMPTY : time;
            } else if ((srcVal.length() == NUM_8)) {
                return getShowTime(srcVal + "000000");
            }
            String time = getShowTime(srcVal);
            if (time.length() >= NUM_5 && time.matches("\\d+")) {
                return Integer.valueOf(time.substring(0, NUM_4)) > Integer.valueOf(getThisYear()) ? EMPTY : time;
            } else {
                return time;
            }
        }
        if (srcVal.contains(DASH) && srcVal.contains(COLON)) {
            return srcVal;
        }
        return EMPTY;
    }

    public static String getThisYear() {
        return new SimpleDateFormat("yyyy").format(System.currentTimeMillis()) + "";
    }

    public static String convertTimeToString(long longTime) {
        return convertTimeToString(longTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String convertTimeToString(long longTime, String format) {
        try {
            Timestamp t = new Timestamp(longTime * 1000L);
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            return sDateFormat.format(t);
        } catch (Exception var5) {
            throw new RuntimeException("Can't format the time by format[" + format + "]!");
        }
    }

    /**
     * 将yyyyMMddHHmmss格式时间转为 yyyy-MM-dd HH:mm:ss
     *
     * @param srcVal 原始值
     * @return
     */
    private static String getShowTime(String srcVal) {
        if (StringUtils.isBlank(srcVal)
                || srcVal.contains(DASH)
                || srcVal.contains(COLON)
                || srcVal.contains(SPACE)
                || (NUM_14 != srcVal.length() && NUM_8 != srcVal.length())) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder("");
        char[] arr = srcVal.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (NUM_3 == i || NUM_5 == i) {
                sb.append(DASH);
            } else if (NUM_7 == i) {
                sb.append(SPACE);
            } else if (NUM_9 == i || NUM_11 == i) {
                sb.append(COLON);
            }
        }
        return sb.toString();
    }

}
