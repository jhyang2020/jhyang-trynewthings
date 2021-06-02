package com.example.jhyangnewthings.api.userlog.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/27
 */

@Repository
public interface LogMapper {

    /**
     * 获取总数
     * @return
     */
    List<Map<String, Object>> getTotalNumOfLogMapper();

}
