package com.tushuangxi.smart.tv.lding.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {

    static Calendar today = Calendar.getInstance();

    /*获取日期*/
    public static String getDay(String date){
        String h;
        String [] day = date.split("-");
        h = day[2];
        return h;
    }

    /*获取月份*/
    public static String getMonth(String date){
        String m;
        String [] day = date.split("-");
        m = day[1];
        return m;
    }

    /*获取年份*/
    public static String getYear(String date){
        String y;
        String [] day = date.split("-");
        y = day[0];
        return y;
    }

    /*获取当前系统时间*/
    public static String getSysDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }

    /*格式化日期时间*/
    public static String formatDatetime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return sdf.format(date);
    }

    public static String formatDatetime(String date) throws ParseException{
        DateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日");
        Date d = fmt.parse(date);
        return d.toString();
    }

    public static String DeviceformatDatetime(String date) throws ParseException{
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        Date d = fmt.parse(date);
        return d.toString();
    }

    public static String formatSystemtime() {
        String currentDateTimeString = new SimpleDateFormat("yyyy/MM/dd ", Locale.US).format(new Date());
        return currentDateTimeString;
    }

    public static String formatDeviceValidity(String time) {
        String currentDateTimeString = null;
        try {
            Date data = new SimpleDateFormat("yyyy/MM/dd ", Locale.US).parse(time);
            currentDateTimeString = new SimpleDateFormat("yyyy/MM/dd ", Locale.US).format(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentDateTimeString;
    }

    public static String nowSystemtime() {
        String currentDateTimeString = new SimpleDateFormat("HH:mm ", Locale.US).format(new Date());
        return currentDateTimeString;
    }

    //2020-08-15 02:13:13.231
    public static String myNowSystemtime() {
        String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
        return currentDateTimeString;
    }

    public static String systemtimeYear() {
        String currentDateTimeString = new SimpleDateFormat("yyyy", Locale.US).format(new Date());
        return currentDateTimeString;
    }



    public static String formatDatetime(String date,int forid){
        if(date == null ||"".equals(date.trim())){
            return "";
        }else{
            String str = "";
            str = date.substring(0,date.indexOf("."));
            String[] array = str.split(" ");
            String[] dates = array[0].split("-");
            switch (forid) {
                case 0:  //yyyy-MM-dd HH:mm:ss
                    str = date.substring(0,date.indexOf("."));
                    break;
                case 1:  //yyyy-MM-dd
                    str = date.substring(0,date.indexOf("."));
                    str = str.substring(0,str.indexOf(" "));
                    break;
                case 2:  //yyyy年MM月dd日 HH:mm:ss
                    str = dates[0]+"年"+dates[1]+"月"+dates[2]+"日 "+array[1];
                    break;
                case 3:  //yyyy年MM月dd日 HH:mm
                    str = dates[0]+"年"+dates[1]+"月"+dates[2]+"日 "+array[1].substring(0, array[1].lastIndexOf(":"));
                    break;
                case 4:  //yyyy年MM月dd日 HH:mm:ss
                    str = dates[0]+"年"+dates[1]+"月"+dates[2]+"日 ";
                    break;
                default:
                    break;
            }
            return str;
        }
    }

    /*获取当前时间的毫秒*/
    public String getSysTimeMillise(){
        long i = System.currentTimeMillis();
        return String.valueOf(i);
    }

    /*获取星期几*/
    public static String getWeek(){
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    public static String formatCommentTime(String str){

        Date date = parse(str, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public static Date parse(String str, String pattern, Locale locale) {
        if(str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*public void getChinaDate(){
        Calendar today = Calendar.getInstance();
        ChineseLunar cl = new ChineseLunar();
        cl.calc(today.get(Calendar.YEAR), today.get(Calendar.MONTH));
        CalElement e = cl.getToday();

        StringBuffer sb = new StringBuffer();
        if (!"".equals(e.solarTerms)) {
            sb.append(e.solarTerms);
        }

        if (!"".equals(e.solarFestival)) {
            sb.append(e.solarFestival);
        }

        if (!"".equals(e.lunarFestival)) {
            sb.append(e.lunarFestival);
        }

        System.out.println("公历：");
        System.out.println(e.sYear + "年" + e.sMonth + "月" + e.sDay + "日");

        System.out.println("农历：");
        System.out.println(e.lYear + "年" + e.lMonth + "月" + e.lDay + "日");
        System.out.println(sb.toString());
    }*/


    //农历月份
    static String[] nlMonth = {"正","二","三","四","五","六","七","八","九","十","十一","十二"};

    //农历日
    static String[] nlday = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十",
            "十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",
            "廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十"};

    //农历天干
    String[] mten={"null","甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};

    //农历地支
    String[] mtwelve={"null","子（鼠）","丑（牛）","寅（虎）","卯（兔）","辰（龙）",
            "巳（蛇）","午（马）","未（羊）","申（猴）","酉（鸡）","戌（狗）","亥（猪）"};

    public static String convertNlYear(String year){
        String maxYear = "";
        for(int i = 0; i < year.length(); i++){
            maxYear = maxYear + minCaseMax(year.substring(i,i+1));
        }
        return maxYear;
    }

    public static String convertNlMoeth(int month){
        String maxMonth = "";
        maxMonth = nlMonth[month - 1];
        return maxMonth;
    }

    public static String convertNlDay(int day){
        String maxDay = "";
        maxDay = nlday[day - 1];
        return maxDay;
    }

    public static String minCaseMax(String str){
        switch (Integer.parseInt(str)) {
            case 0:
                return "零";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";

            default:
                return "null";
        }
    }
    }

