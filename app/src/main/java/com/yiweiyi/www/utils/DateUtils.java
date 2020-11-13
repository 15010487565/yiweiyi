package com.yiweiyi.www.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gs on 2020/11/3.
 */
public class DateUtils {

    public static String getTine(long time) {

        //获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        //定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try{
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);
            Date date = new Date(time * 1000);
            if(date.after(paseBeginTime) && date.before(paseEndTime)) {
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
                return dateFormat1.format(date);
            }else {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat2.format(date);
            }
        } catch(Exception e){

        }

        return "";
    }

}
