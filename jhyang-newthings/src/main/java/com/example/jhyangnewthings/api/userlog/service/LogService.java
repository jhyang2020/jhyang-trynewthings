package com.example.jhyangnewthings.api.userlog.service;

import com.example.jhyangnewthings.constructor.datasource.annotation.TargetDatasource;
import com.example.jhyangnewthings.api.userlog.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import static com.example.jhyangnewthings.constructor.datasource.constant.DatasourceConstant.LOG_PG_DATA_SOURCE;

/**
 * @author jhYang
 * @Description
 * @Date 2020/3/27
 */
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @TargetDatasource()
    public List<Map<String, Object>> getTotalNumOfLog() {
        List<Map<String, Object>> list = logMapper.getTotalNumOfLogMapper();
        return list;
    }
}
