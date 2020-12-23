package com.iris.todolist.widget;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.iris.todolist.activity.AddTodoActivity;

import java.util.Calendar;

public class DateAndTimePickerDialog {

    static OnDateAndTimePickedListener onDateAndTimePickedListener;

    static int pickerYear;
    static int pickerMonth;
    static int pickerDay;
    static int pickerHour;
    static int pickerMinute;

    public static void showDateAndTimePickerDialog(Context context, OnDateAndTimePickedListener listener) {
        showDatePickerDialog(context);
        onDateAndTimePickedListener = listener;
    }

    private static void showDatePickerDialog(Context context) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Toast.makeText(AddTodoActivity.this, year+"/"+month+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
                        showTimePickerDialog(context);
                        pickerYear = year;
                        pickerMonth = month;
                        pickerDay = dayOfMonth;
                    }
                }, nowYear,nowMonth,nowDay);
        datePickerDialog.show();
    }

    private static void showTimePickerDialog(Context context) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        pickerHour = hourOfDay;
                        pickerMinute = minute;
                        String pickedTime = pickerYear+"/"+(pickerMonth + 1)+"/"+pickerDay+" "+pickerHour+":"+pickerMinute;

                        if(onDateAndTimePickedListener != null) {
                            onDateAndTimePickedListener.onDateAndTimePicked(pickedTime);
                        }
                    }
                },  0, 0, true);
        timePickerDialog.show();
    }

    public interface OnDateAndTimePickedListener {
        public  void onDateAndTimePicked(String pickedTime);
    }

}
