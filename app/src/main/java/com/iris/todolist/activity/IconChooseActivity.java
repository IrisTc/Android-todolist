 package com.iris.todolist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iris.todolist.R;
import com.iris.todolist.adapter.IconAdapter;

import java.util.ArrayList;
import java.util.List;

 public class IconChooseActivity extends AppCompatActivity {
     ListView lvIcons;
     List<Pair<Integer, String>> icons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_choose);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("选择Icon");

        lvIcons = (ListView) findViewById(R.id.lv_icons);
        lvIcons.setAdapter(new IconAdapter(IconChooseActivity.this, icons));

        lvIcons.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("chosenIconId", icons.get(position).first);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

     private void initData() {
         icons.add(new Pair<>(R.drawable.ic_bell, "bell"));
         icons.add(new Pair<>(R.drawable.ic_candle, "candle"));
         icons.add(new Pair<>(R.drawable.ic_crutch, "crutch"));
         icons.add(new Pair<>(R.drawable.ic_deer, "deer"));
         icons.add(new Pair<>(R.drawable.ic_gift, "gift"));
         icons.add(new Pair<>(R.drawable.ic_glove, "glove"));
         icons.add(new Pair<>(R.drawable.ic_santa, "santa"));
         icons.add(new Pair<>(R.drawable.ic_snowflake, "snowflake"));
         icons.add(new Pair<>(R.drawable.ic_sock, "sock"));
     }
 }