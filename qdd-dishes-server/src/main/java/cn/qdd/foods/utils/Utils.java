package cn.qdd.foods.utils;


import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 东方曜
 */
public class Utils {


    /**
     * String 转 Date
     */
    public static Date formatDate(String dateString, String formatText)
            throws ParseException {
        DateFormat format = new SimpleDateFormat(formatText);
        return format.parse(dateString);
    }


    public static String getCurrentDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getCurrentDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    public static Map<String, String> lastDate(String start, String end) {
        try {
            Date startDate = Utils.formatDate(start, "yyyy-mm-dd");
            Date endDate = Utils.formatDate(end, "yyyy-mm-dd");

            long srcStart = startDate.getTime() - (endDate.getTime() - startDate.getTime()) - 86400000;
            long srcEnd = startDate.getTime() - 86400000;

            return new HashMap<String, String>() {{
                put("start", Utils.getCurrentDate(new Date(srcStart), "yyyy-mm-dd"));
                put("end", Utils.getCurrentDate(new Date(srcEnd), "yyyy-mm-dd"));
            }};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取上一天字符串
     *
     * @param day day
     * @return java.lang.String
     * @author 东方曜
     * @date 2020/12/1
     */
    public static String lastDate(String day) {
        try {
            Date startDate = Utils.formatDate(day, "yyyy-mm-dd");
            long lastDate = startDate.getTime() - 86400000;
            return Utils.getCurrentDate(new Date(lastDate), "yyyy-mm-dd");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 计算比值
     *
     * @param num1 num1
     * @param num2 num2
     * @return java.lang.String
     * @author 东方曜
     * @date 2020/12/1
     */
    public static String rate(Integer num1, Integer num2) {

        if (Objects.isNull(num2) || num2 == 0 || Objects.isNull(num1)) {
            return "0.00%";
        }
        return new DecimalFormat("0.00").format((num1.floatValue() / num2.floatValue()) * 100) + "%";
    }

    public static String calcDod(Integer num1, Integer num2) {

        if (Objects.isNull(num2) || num2 == 0 || Objects.isNull(num1)) {
            return "0.00%";
        }
        return new DecimalFormat("0.00").format(((num1.floatValue() - num2.floatValue()) / num2.floatValue()) * 100) + "%";
    }

    public static String rate(String str1, String str2) {
        if (Strings.isEmpty(str2) || Strings.isEmpty(str1)) {
            return "0.00%";
        }
        double num1 = new BigDecimal(str1.replace("%", "")).doubleValue();
        double num2 = new BigDecimal(str2.replace("%", "")).doubleValue();
        if (num2 == 0) {
            return "0.00%";
        }
        return new DecimalFormat("0.00").format(((num1 - num2) / num2) * 100) + "%";
    }

    public static String avg(Integer num1, Integer num2) {
        if (Objects.isNull(num2) || num2 == 0 || Objects.isNull(num1)) {
            return "0.00";
        }
        double i = (double) num1 / num2;
        return String.format("%.2f", i);
    }

    public static String strReplace(String str) {
        return str.replace("'", "").replace("[", "").replace("]", "");
    }


    public static void main(String[] args) {
        Integer day = 1000;
        Integer day2 = 10;
        String rate = avg(13912316, 16992613);


        System.out.println(rate);


    }

    /**
     * 获取两个日期之间的所有日期
     */
    public static List<String> creatDays(String start, String end) {
        ArrayList<String> days = new ArrayList<>();
        Date endtime = null;
        try {
            Date bigtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start + " 00:00:00");
            endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end + " 00:00:00");
            //定义一个接受时间的集合
            List<Date> lDate = new ArrayList<>();
            lDate.add(bigtime);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(bigtime);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(endtime);
            // 测试此日期是否在指定日期之后
            while (endtime.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }
            for (Date date : lDate) {
                days.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 将以逗号分割的字符加上单引号
     */
    public static String createWhereIn(String str) {
        String[] split = str.split(",");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add("'" + s.trim() + "'");
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * 拼接插入数据的命令
     */
    public static String getSqlOrder(String sql) {
        return "clickhouse-client -h cc-2ze8v0ry3xhvk3u31.ads.aliyuncs.com --port 3306 -u ckadmin --password BAYJusr6cp --query=\"" + sql + "\"";
    }


}