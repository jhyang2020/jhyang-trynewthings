package com.example.jhyangnewthings.userlog.service;

import com.example.jhyangnewthings.constructor.datasource.annotation.TargetDatasource;
import com.example.jhyangnewthings.userlog.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import static com.example.jhyangnewthings.constructor.datasource.constant.DatasourceConstant.MYSQL_DATA_SOURCE;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/27
 */
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @TargetDatasource(MYSQL_DATA_SOURCE)
    public List<Map<String, Object>> getTotalNumOfLog() {
        List<Map<String, Object>> list = logMapper.getTotalNumOfLogMapper();
        return list;
    }
}
