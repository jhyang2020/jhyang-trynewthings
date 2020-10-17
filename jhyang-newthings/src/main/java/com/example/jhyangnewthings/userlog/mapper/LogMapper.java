package com.example.jhyangnewthings.userlog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/27
 */
@Mapper
@Repository
public interface LogMapper {

    List<Map<String, Object>> getTotalNumOfLogMapper();

}
