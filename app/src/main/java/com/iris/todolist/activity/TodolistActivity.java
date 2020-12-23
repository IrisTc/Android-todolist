package com.iris.todolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.iris.todolist.R;
import com.iris.todolist.adapter.GroupAdapter;
import com.iris.todolist.adapter.TodoAdapter;
import com.iris.todolist.model.TodoModel;
import com.iris.todolist.utils.DataService;
import com.iris.todolist.utils.ListUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TodolistActivity extends AppCompatActivity {

    String groupName;
    ListView lvTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(groupName);
        setSupportActionBar(toolbar);

        lvTodos = (ListView) findViewById(R.id.lv_todos);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


    private void getData() {
        List<TodoModel> todoModels = DataService.getTodoList(TodolistActivity.this, groupName);
        lvTodos.setAdapter(new TodoAdapter(TodolistActivity.this, todoModels));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_add:
                Intent intent = new Intent(TodolistActivity.this, AddTodoActivity.class);
                intent.putExtra("groupName", groupName);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}