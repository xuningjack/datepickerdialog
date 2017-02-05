package com.example.util;

import java.util.Date;

import android.content.Context;

import com.example.android_datepickerdialog.R;


/**
 * 日期操作工具类
 * @author Jack  
 * @version 创建时间：2013-9-2  下午4:32:03
 */
public class DateUtils {

	/**
     * 根据生日得到星座信息
     * 
     * @param birthday 格式如1994-3-13
     * @return
     */
    public static String getConstellation(Context context, String birthday) {

        int month = 0;
        int day = 0;
        String[] temp = birthday.split("-");
        String result = "";
        
        if(temp != null && temp.length == 3) {
        	
            month = removeZero(temp[1]);		//得到月份
            day = removeZero(temp[2]);			//得到日
            int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };
            String[] constellationArr = context.getResources().getStringArray(R.array.list_constellation);
            result = day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];   
        }
        return result;
    }
    
    /**
     * 去除截取日、月字符串前面的0
     * @param str
     * @return
     */
    public static int removeZero(String str){
    	
    	int result = 1;
    	if(str.startsWith("0")){		//以0开头的月份去掉0
    		
    		result = Integer.parseInt(str.substring(1));
    	}else{
    		
    		result = Integer.parseInt(str);
    	}
    	return result;
    }
    
    /**
     * 在月、日前面加0
     * @param str
     * @return
     */
    public static String addZero(String str){
    	
    	StringBuilder builder = new StringBuilder();
    	int month = 0;
        int day = 0;
        String[] temp = str.split("-");
        
        if(temp != null && temp.length == 3) {
        	
        	builder.append(temp[0]);
            month = removeZero(temp[1]);		//得到月份
            day = removeZero(temp[2]);			//得到日	
            if(month > 0 && month < 10){
            	
            	builder.append("-0" + month);
            }else{
            	
            	builder.append("-" + month);
            }
            if(day > 0 && day < 10){
            	
            	builder.append("-0" + day);
            }else{
            	
            	builder.append("-" + day);
            }
            
        }
    	return builder.toString();
    }
    
    /**
     * 获取指定日期的毫秒数
     */
    public static long getMissSecondFromData(int year, int month, int day) {
    	
        Date date = new Date(year - 1900, month, day);
        return date.getTime();
    }
}