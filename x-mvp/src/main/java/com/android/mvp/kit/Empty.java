package com.android.mvp.kit;

import java.util.List;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public  class Empty {


    public static boolean check(Object obj) {
        return obj == null;
    }

    public static boolean check(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean check(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean check(String str) {
        return str == null || "".equals(str);
    }


}