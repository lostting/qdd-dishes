package cn.qdd.foods.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 东方曜
 */
public class CronExpressionUtils {

    /**
     * 格式的日期转换为cron表达式
     *
     * @return cron表达式
     */
    public static String getCronExpressionFromFormatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        return sec + " " + min + " " + hour + " " + day + " " + month + " " + "?" + " " + year;
    }

    /**
     * 每天执行
     *
     * @param date date
     * @return java.lang.String
     * @author 东方曜
     * @date 2020/9/15
     */
    public static String getDailyCronExpressionFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        return sec + " " + min + " " + hour + " * * ?";
    }

    /**
     * 每周执行
     *
     * @param date    date
     * @param pattern pattern 周几  4, 5,6
     * @return java.lang.String
     * @author 东方曜
     * @date 2020/9/15
     */

    public static String getWeeklyCronExpressionFromDate(Date date, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        return sec + " " + min + " " + hour + " ? * " + pattern;
    }

    /**
     * 每个月的几号几号执行
     *
     * @param date    date
     * @param pattern pattern 1,2,3,4,5,6
     * @return java.lang.String
     * @author 东方曜
     * @date 2020/9/15
     */
    public static String getMonthlyCronExpressionFromDate(Date date, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        return sec + " " + min + " " + hour + " " + pattern + " * ?";
    }

//    public static void main(String args[]) throws Exception{
//        System.out.println("固定时间(单次)");
//        testFixed("2014-10-10 10:20");
//
//        System.out.println("每天-固定时间");
//        testDaily("00:00");
//        testDaily("10:20");
//        testDaily("23:59");
//
//        System.out.println("每周几-固定时间");
//        testWeekly("00:00", "1,2,3,4,5,6,7");
//        testWeekly("10:20", "1");
//        testWeekly("23:59", "2,5,6,7");
//
//        System.out.println("每月日期-固定时间");
//        testMonthly("00:00", "1");
//        testMonthly("10:20", "2,4,9,31");
//        testMonthly("23:59", "1,2,3,4,5,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31");
//    }
//
//    public static void testFixed(String dateStr)  throws Exception{
//        //String dateStr = "2014-10-10 10:20";
//        DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = fmt1.parse(dateStr);
//        String cron = CronExpressionUtils.getCronExpressionFromFormatDate(date);
//        System.out.println(cron);
//    }
//
//    public static void testDaily(String dateStr) throws Exception {
//        //String dateStr = "10:20";
//        DateFormat fmt1 = new SimpleDateFormat("HH:mm");
//        Date date = fmt1.parse(dateStr);
//        String cron = CronExpressionUtils.getDailyCronExpressionFromDate(date);
//        System.out.println(cron);
//    }
//
//    public static void testWeekly(String dateStr, String pattern) throws Exception {
//        //String dateStr = "10:20";
//        DateFormat fmt1 = new SimpleDateFormat("HH:mm");
//        //String pattern = "2,4,6";
//        Date date = fmt1.parse(dateStr);
//        String cron = CronExpressionUtils.getWeeklyCronExpressionFromDate(date, pattern);
//        System.out.println(cron);
//    }
//
//    public static void testMonthly(String dateStr, String pattern) throws Exception {
//        //String dateStr = "10:20";
//        DateFormat fmt1 = new SimpleDateFormat("HH:mm");
//        //String pattern = "2,6,10";
//        Date date = fmt1.parse(dateStr);
//        String cron = CronExpressionUtils.getMonthlyCronExpressionFromDate(date, pattern);
//        System.out.println(cron);
//    }
}
