package com.iris.todolist.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.iris.todolist.model.TodoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataService {
    public static List<TodoModel> getTodoList(Context context, String groupName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        Map<String,String> map = (Map<String, String>) sharedPreferences.getAll();
        Set<Map.Entry<String,String>> entrys = map.entrySet();

        List<TodoModel> todoModels = new ArrayList<>();
        for(Map.Entry<String,String> entry : entrys) {
            todoModels.add(new TodoModel(entry));
        }

        Collections.sort(todoModels, new Comparator<TodoModel>() {
            @Override
            public int compare(TodoModel o1, TodoModel o2) {
                return (int)(o1.getCreateTime()-o2.getCreateTime());
            }
        });
        return todoModels;
    }

    public static void saveTodo(Context context, String groupName, TodoModel todoModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(todoModel.getName(), todoModel.toString());
        editor.commit();
    }
}
