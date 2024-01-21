package com.example.discuss;

import java.util.List;

public class Helpers {

    public static <T> String[] listToArray(List<T> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).toString();
        }
        return array;
    }

}
