package com.iris.todolist.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iris.todolist.R;
import com.iris.todolist.model.TodoModel;

import java.util.List;
import java.util.Map;

public class TodoAdapter extends MyBaseAdapter<TodoModel> {

    public TodoAdapter(Context context, List<TodoModel> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //节省内存开销
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_todo, parent, false);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_todo);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(data.get(position).getName());

        return convertView;
    }

    class ViewHolder{
         TextView tvName;
    }
}
