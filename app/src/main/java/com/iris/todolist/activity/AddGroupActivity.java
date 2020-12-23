package com.iris.todolist.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iris.todolist.R;

public class AddGroupActivity extends AppCompatActivity {
    public static final int REQUESTCODE_ICON_CHOOSE = 888;
    ImageView ivChosenIcon;
    int chosenIconId = -1;
    EditText etGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("添加分组");
        setSupportActionBar(toolbar);

        ivChosenIcon = (ImageView) findViewById(R.id.iv_chosen_icon);
        etGroupName = (EditText) findViewById(R.id.et_group_name);

        RelativeLayout ri_icon_chooose = (RelativeLayout) findViewById(R.id.ri_icon_choose);
        ri_icon_chooose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGroupActivity.this, IconChooseActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, REQUESTCODE_ICON_CHOOSE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESTCODE_ICON_CHOOSE && resultCode == RESULT_OK) {
            chosenIconId = data.getIntExtra("chosenIconId", -1);
//            Toast.makeText(AddGroupActivity.this, "id:"+chosenIconId, Toast.LENGTH_SHORT).show();
            ivChosenIcon.setImageResource(chosenIconId);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String groupName = etGroupName.getText().toString().trim();
        if(groupName == null || "".equals(groupName)) {
            Toast.makeText(AddGroupActivity.this, "请输入分组名", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(chosenIconId == -1) {
            Toast.makeText(AddGroupActivity.this, "请选择icon", Toast.LENGTH_SHORT).show();
            return true;
        }
        saveGroup(groupName);
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void saveGroup(String groupName) {
        SharedPreferences sharedPreferences = getSharedPreferences("groups", MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(groupName, chosenIconId);
        editor.commit();
    }
}
