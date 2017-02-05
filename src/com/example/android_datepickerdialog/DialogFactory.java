package com.example.android_datepickerdialog;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.widget.TextView;


/**
 * 自定义的dialog工厂类
 * @author Jack
 * @version 创建时间：2013-10-28 下午3:41:27
 */
public class DialogFactory {


	/**
	 * 日期选择对话框
	 * @param context
	 * @param listener
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static MyDatePickerDialog createPickerDialog(Context context, TextView input,OnDateSetListener listener, int year, int month, int day) {

		return new MyDatePickerDialog(context, input, listener, year, month, day);
	}
}