package com.example.android_datepickerdialog;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;



/**
 * 自定义选择日期dialog
 * @author Jack
 * @version 创建时间：2014年5月29日 上午11:45:36
 */
public class MyDatePickerDialog extends DatePickerDialog {

	private final String TAG = "MyDatePickerDialog";
	
	public MyDatePickerDialog(Context context, TextView input, 
			OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {

		super(context, callBack, year, monthOfYear, dayOfMonth);
		this.setTitle(context.getString(R.string.choose_birthday));
		Calendar calendar = Calendar.getInstance();
		getDatePicker().setMaxDate(calendar.getTimeInMillis()); // 年龄选择范围不能超过今天
		calendar.set(1949, 9, 1);
		getDatePicker().setMinDate(calendar.getTimeInMillis()); // 年龄范围不能早于建国前
		changeStyle(getDatePicker());
		updateDate(year, monthOfYear, dayOfMonth);  		 //注意：更新设置当前日期
	}
	
	/**
	 * 改变DatePicker的样式
	 * @param datePicker
	 */
	public void changeStyle(DatePicker datePicker){
		
		View v_month2 = null;
		
		Field[] fields = DatePicker.class.getDeclaredFields();
		//获取DatePicker中的属性
		for(Field field : fields) {
			field.setAccessible(true);
			if(field.getType().getSimpleName().equals("NumberPicker")) {
				try {
					v_month2 = (View)field.get(datePicker);
				} catch (Exception e) {
					Log.e(TAG, e.getMessage());
				} 
			}
		}
		
		//改变Month的宽度
		if(v_month2 != null) {
			v_month2.measure(0, 0);
			v_month2.getLayoutParams().width = (int) (v_month2.getMeasuredWidth() * 2f);   //月份价宽了
		}
	}
}