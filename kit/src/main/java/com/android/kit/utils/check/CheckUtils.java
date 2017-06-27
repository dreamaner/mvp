package com.android.kit.utils.check;

import java.util.Collection;
import java.util.Map;

/**
 * 判空
 *
 * Created by Dreamaner on 2017/5/15.
 */

public class CheckUtils {

    public static boolean isEmpty(CharSequence str) {
        return isNull(str) || str.length() == 0;
    }

    public static boolean isEmpty(Object[] obj) {
        return isNull(obj) || obj.length == 0;
    }

    public static boolean isEmpty(Collection<?> list) {
        return isNull(list) || list.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

}
