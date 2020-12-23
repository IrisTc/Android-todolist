package com.iris.todolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iris.todolist.R;
import com.iris.todolist.model.TodoModel;
import com.iris.todolist.utils.DataService;
import com.iris.todolist.widget.DateAndTimePickerDialog;

import java.util.Calendar;

public class AddTodoActivity extends AppCompatActivity {
    String groupName;
    TextView tvPickedTime;
    EditText etTodoName;
    CheckBox cbNeedNotify;
    String pickedTime;
    String todoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("添加任務");
        setSupportActionBar(toolbar);

        etTodoName = (EditText) findViewById(R.id.et_todo_name);
        cbNeedNotify = (CheckBox) findViewById(R.id.cb_need_notify);
        tvPickedTime = (TextView) findViewById(R.id.tv_picked_time);

        findViewById(R.id.ri_time_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateAndTime();
            }
        });
    }

    private void pickDateAndTime() {
        DateAndTimePickerDialog.showDateAndTimePickerDialog(AddTodoActivity.this,
            new DateAndTimePickerDialog.OnDateAndTimePickedListener() {
                 @Override
                 public void onDateAndTimePicked(String time) {
                     pickedTime = time;
                     tvPickedTime.setText(pickedTime);
                 }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        todoName = etTodoName.getText().toString().trim();
        if(etTodoName == null || "".equals(etTodoName)) {
            Toast.makeText(AddTodoActivity.this, "请输入任务名称", Toast.LENGTH_SHORT).show();
            return true;
        }
        saveTodo();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void saveTodo() {
        boolean needNotify = cbNeedNotify.isChecked();
        DataService.saveTodo(AddTodoActivity.this, groupName,
                new TodoModel(System.currentTimeMillis(),needNotify,pickedTime,todoName));
    }
}