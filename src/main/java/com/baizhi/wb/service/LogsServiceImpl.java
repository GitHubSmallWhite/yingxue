package com.baizhi.wb.service;

import com.baizhi.wb.annotcation.AddRedis;
import com.baizhi.wb.annotcation.DelRedis;
import com.baizhi.wb.dao.LogsMapper;
import com.baizhi.wb.entity.Logs;
import com.baizhi.wb.entity.LogsExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service(value = "logsService")
public class LogsServiceImpl implements LogsService{
    @Resource
    private LogsMapper logsMapper;
    @AddRedis
    @Override
    public HashMap<String, Object> findLogs(Integer pageNumber, Integer showNumber) {
        HashMap<String,Object> map=new HashMap<>();
        LogsExample logsExample = new LogsExample();
        RowBounds rowBounds = new RowBounds((pageNumber - 1) * showNumber, showNumber);
        List<Logs> logs = logsMapper.selectByExampleAndRowBounds(logsExample, rowBounds);
        int count = logsMapper.selectCountByExample(logsExample);
        int total=count%showNumber==0?count/showNumber:count/showNumber+1;
        map.put("page",pageNumber);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",logs);
        return map;
    }
    @DelRedis
    @Override
    public void delete(Logs logs) {
        logsMapper.delete(logs);
    }
}
