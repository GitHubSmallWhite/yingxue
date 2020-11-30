package com.baizhi.wb.aspect;

import com.baizhi.wb.annotcation.AddLog;
import com.baizhi.wb.dao.LogsMapper;
import com.baizhi.wb.entity.Admin;
import com.baizhi.wb.entity.Logs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    private LogsMapper logsMapper;
    @Around("@annotation(com.baizhi.wb.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        //获取用户名
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //获取方法名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);
        //获取注解的值
        String value=addLog.value();//操作
        String message=null;
        Object result=null;
        try {
            result = proceedingJoinPoint.proceed();
            message="success";
        } catch (Throwable throwable) {
            message="error";
        }
        Logs logs = new Logs(UUID.randomUUID().toString(), admin.getUsername(), value, new Date(), message);
        logsMapper.insert(logs);
        return result;
    }
}
