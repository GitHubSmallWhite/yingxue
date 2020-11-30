package com.baizhi.wb.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.wb.entity.Logs;
import com.baizhi.wb.service.LogsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("logs")
public class LogsController {
    @Resource
    private LogsService logsService;
    @RequestMapping("showLogs")
    @ResponseBody
    public Map<String,Object> showLogs(Integer page,Integer rows){
     return logsService.findLogs(page,rows);
    }
    @RequestMapping("setLogs")
    @ResponseBody
    public void setLogs(String oper, Logs logs){
        if (StringUtils.equals(oper,"del")){
            logsService.delete(logs);
        }
    }
}
