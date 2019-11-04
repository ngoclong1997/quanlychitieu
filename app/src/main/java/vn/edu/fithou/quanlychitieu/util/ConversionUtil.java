package vn.edu.fithou.quanlychitieu.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversionUtil {
    public static String doubleToString(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##");
        return decimalFormat.format(num);
    }
    public static String doubleToString(double num, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(num);
    }
    public static String intToString(int num) {
        return String.valueOf(num);
    }
    public static int stringToInt(String str, int defNum) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return defNum;
        }
    }
    public static String getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return "Thứ Hai";
            case Calendar.TUESDAY:
                return "Thứ Ba";
            case Calendar.WEDNESDAY:
                return "Thứ Tư";
            case Calendar.THURSDAY:
                return "Thứ Năm";
            case Calendar.FRIDAY:
                return "Thứ Sáu";
            case Calendar.SATURDAY:
                return "Thứ Bảy";
            case Calendar.SUNDAY:
                return "Chủ Nhật";
            default:
                return "Thứ High";
        }
    }

    public static String getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
    }

    public static String getMonthAndYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return "tháng " + month + " " + year;
    }
}