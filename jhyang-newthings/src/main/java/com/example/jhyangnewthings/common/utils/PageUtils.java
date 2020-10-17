package com.example.jhyangnewthings.common.utils;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class PageUtils {
    /**
     * 对List进行分页
     *
     * @param result
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static List<Map<String, Object>> pageingListResult(List<Map<String, Object>> result, int pageNo, int pageSize) {
        int totalSize = result.size();
        int pageNos = totalSize / pageSize + 1;
        if (pageNo * pageSize <= totalSize) {
            return result.subList(pageSize * (pageNo - 1), pageSize * pageNo);
        } else if (pageNo * pageSize > totalSize && totalSize < pageSize && pageNo == pageNos) {
            return result.subList(0, totalSize);
        } else if (pageNo * pageSize > totalSize && totalSize > pageSize && pageNo == pageNos) {
            return result.subList(pageSize * (pageNo - 1), totalSize);
        } else {
            return result;
        }
    }

    public static Map<String, Object> pageingValue(int totalSize, int pageNo, int pageSize) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalSize", totalSize);
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        return result;
    }
}
