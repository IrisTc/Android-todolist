package com.iris.todolist.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iris.todolist.R;

import java.util.List;
import java.util.Map;

public class   extends MyBaseAdapter<Map.Entry<String,Integer>> {
    public GroupAdapter(Context context, List<Map.Entry<String, Integer>> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IconAdapter.ViewHolder viewHolder;
        //节省内存开销
        if (convertView == null) {
            viewHolder = new IconAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_icon_choose, parent, false);

            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IconAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.ivIcon.setImageResource(data.get(position).getValue());
        viewHolder.tvName.setText(data.get(position).getKey());

        return convertView;
    }
}
