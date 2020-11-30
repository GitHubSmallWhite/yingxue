package com.baizhi.wb.service;

import com.baizhi.wb.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    public String login(Admin admin, HttpServletRequest request,String code);
}
