package com.baizhi.wb.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {
    public HashMap<String,Object> findList(Integer pageNumber, Integer showNumber);
    public HashMap<String,Object> updateStatus(String id);
    public void UsersExport(HttpServletRequest request);
    public void UsersImport(HttpServletRequest request);
}
