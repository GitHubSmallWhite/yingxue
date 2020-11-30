package com.baizhi.wb.service;

import com.baizhi.wb.entity.Logs;

import java.util.HashMap;

public interface LogsService {
    public HashMap<String,Object> findLogs(Integer pageNumber,Integer showNumber);
    public void delete(Logs logs);
}

