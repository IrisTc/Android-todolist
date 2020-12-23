package com.iris.todolist.model;

import java.util.Map;

public class TodoModel {
    private long createTime;
    private boolean needNotify;
    private String notifyTime;
    private String name;

    @Override
    public String toString() {
        return needNotify + "&" + notifyTime + "&" + createTime;
    }

    public TodoModel(long createTime, boolean needNotify, String notifyTime, String name) {
        this.createTime = createTime;
        this.needNotify = needNotify;
        this.notifyTime = notifyTime;
        this.name = name;
    }

    public TodoModel(Map.Entry<String,String> entry) {
        this.name = entry.getKey();
        String value = entry.getValue();
        String[] fields = value.split("&");
        this.needNotify = Boolean.parseBoolean(fields[0]);
        this.notifyTime = fields[1];
        this.createTime = Long.parseLong(fields[2]);
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isNeedNotify() {
        return needNotify;
    }

    public void setNeedNotify(boolean needNotify) {
        this.needNotify = needNotify;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
