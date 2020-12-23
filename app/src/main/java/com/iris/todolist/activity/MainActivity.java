package com.iris.todolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.iris.todolist.R;
import com.iris.todolist.adapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView lvGroups;
    List<Map.Entry<String,Integer>> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("分组列表");
//        toolbar.setSubtitle("sbutitle");
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        lvGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String groupName = groups.get(position).getKey();
                Intent intent = new Intent(MainActivity.this, TodolistActivity.class);
                intent.putExtra("groupName", groupName);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        lvGroups = (ListView) findViewById(R.id.lv_groups);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


    private void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("groups", MODE_PRIVATE);
        Map<String,Integer> map = (Map<String, Integer>) sharedPreferences.getAll();
        Set<Map.Entry<String,Integer>> set = map.entrySet();
        groups = new ArrayList<>(set);
        lvGroups.setAdapter(new GroupAdapter(MainActivity.this, groups));
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
        switch(itemId){
            case R.id.item_add:
                Intent intent = new Intent(MainActivity.this, AddGroupActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}