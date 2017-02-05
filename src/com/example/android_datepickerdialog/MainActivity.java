package com.example.android_datepickerdialog;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.example.util.Constants;
import com.example.util.DateUtils;


/**
 * 单击日期textview弹出选择日期dialog
 * @author Jack
 * @version 创建时间：2014年5月29日  上午11:27:44
 */
public class MainActivity extends Activity {

	private TextView mBirthday, mConstellation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBirthday = (TextView)findViewById(R.id.birthday);
		mConstellation = (TextView)findViewById(R.id.constellation);
		mBirthday.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				int year = Calendar.getInstance().get(Calendar.YEAR);
                int month = Calendar.getInstance().get(Calendar.MONTH) - 1;
                int day = Calendar.getInstance().get(Calendar.DATE);
                String content = mBirthday.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    String[] array = content.split("-");
                    year = Integer.valueOf(array[0]);
                    month = Integer.valueOf(array[1]) - 1;
                    day = Integer.valueOf(array[2]);
                }
                final StringBuffer sBuffer = new StringBuffer();

                MyDatePickerDialog datePickerDialog = DialogFactory.createPickerDialog(MainActivity.this, mBirthday, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (DateUtils.getMissSecondFromData(year + Constants.MIN_YEAR, monthOfYear, dayOfMonth) - System.currentTimeMillis() > 0) {  //年龄不得小于16岁
                        	
                            Toast.makeText(MainActivity.this, getString(R.string.nianling_xianzhi), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        sBuffer.setLength(0); // 清除缓存
                        sBuffer.append(year).append("-").append(monthOfYear + 1).append("-").append(dayOfMonth);
                        String constellation = DateUtils.getConstellation(MainActivity.this, sBuffer.toString());
                        mBirthday.setText(DateUtils.addZero(sBuffer.toString()));
                        mConstellation.setText(constellation);
                    }
                }, year, month, day);
                datePickerDialog.show();
			}
		});
	}
}