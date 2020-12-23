package com.iris.todolist.utils;

import java.util.List;
import java.util.Map;

public class ListUtil {

    public interface Comparator{
        public  boolean comparat(int i, int j);
    }
    public static <T> void sort(List<T> list, Comparator comparator) {
        //冒泡排序
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(comparator.comparat(i, j)) {
                    T t = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, t);
                }
            }
        }
    }
}
