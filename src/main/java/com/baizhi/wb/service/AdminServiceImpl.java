package com.baizhi.wb.service;

import com.baizhi.wb.dao.AdminMapper;
import com.baizhi.wb.entity.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public String login(Admin admin, HttpServletRequest request,String code) {
        HttpSession session = request.getSession();
        if (admin!=null){//所传数据不为空进行判断
            Admin admin1 = new Admin();//查询的对象和结果所存对象
            admin1.setUsername(admin.getUsername());
            admin1 = adminMapper.selectOne(admin1);//查询结果替换查询对象
            if(admin1!=null){//账号存在判断密码
                //获取验证码
                String code1 = (String) session.getAttribute("code");
                if(admin.getPassword().equals(admin1.getPassword())){//密码相同登陆成功
                   if(code1.equals(code)) {
                       session.setAttribute("admin", admin);//存入session作用域
                        session.removeAttribute("massage");
                       return "yes";
                   }else {
                       session.setAttribute("massage","验证码错误");
                   }
                }else {
                    session.setAttribute("massage","密码错误");
                }
            }else {
                session.setAttribute("massage","账号不存在");
            }
        }else {
            session.setAttribute("massage","请输入正确信息");
        }
        return "no";
    }
}
