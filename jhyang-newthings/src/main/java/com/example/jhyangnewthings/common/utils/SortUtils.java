package com.example.jhyangnewthings.common.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Author jhYang
 * @Date 2020/4/13 0013 15:19
 * @Discription todo
 */
public class SortUtils {

    /**
     * 从大到小排序
     *
     * @param listSort
     * @param sort
     * @return
     */
    public static List<Map<String, String>> sortListByMapValueSort(List<Map<String, String>> listSort, String sort) {
        List<Map<String, String>> listReturn = listSort;
        Collections.sort(listReturn, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                int count = 0;
                double a = 0;
                try {
                    a = Double.parseDouble(o2.get(sort) + "");
                    double b = Double.parseDouble(o1.get(sort) + "");
                    count = Double.compare(a, b);
                } catch (NumberFormatException e) {
                    count = 0;
                }

                return count;
            }
        });
        return listReturn;
    }

    public static List<Map<String, String>> sortListByMapValueSort2(List<Map<String, String>> listSort, String sort) {
        List<Map<String, String>> listReturn = listSort;
        Collections.sort(listReturn, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return  Double.compare(Double.parseDouble(o2.get(sort) + "") , Double.parseDouble(o1.get(sort) + ""));
            }
        });
//        listReturn.sort(Comparator.comparingDouble(Map :: getOrDefault(sort,"")));

        return listReturn;
    }

    public static List<Map<String, String>> sortListByMapValueSort3(List<Map<String, String>> listSort, String sort) {
        List<Map<String, String>> listReturn = listSort;
        Collections.sort(listReturn, (Map<String, String> o1, Map<String, String> o2) ->
                        Double.compare(Double.parseDouble(o2.get(sort) + ""), Double.parseDouble(o1.get(sort) + ""))
        );
        return listReturn;
    }





}
