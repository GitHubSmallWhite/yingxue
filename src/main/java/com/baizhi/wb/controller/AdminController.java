package com.baizhi.wb.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.wb.entity.Admin;
import com.baizhi.wb.entity.Category;
import com.baizhi.wb.service.AdminService;
import com.baizhi.wb.service.CategoryService;
import com.baizhi.wb.service.UserService;
import com.baizhi.wb.util.AliyunUtil;
import com.baizhi.wb.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
//    登录
    @RequestMapping("login")
    public String login(Admin admin, HttpServletRequest request,String code){
        String login = adminService.login(admin,request,code);
        if(login.equals("yes")){
            return "main/main";
        }else{
            return "redirect:/login/login.jsp";
        }
    }
//    验证码
    @RequestMapping("code")
    @ResponseBody
    public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String securityCode = ImageCodeUtil.getSecurityCode();//随机验证码
        BufferedImage image = ImageCodeUtil.createImage(securityCode);//图片输出流
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"gif",outputStream);
        System.out.println(securityCode);//控制台输出验证码
        request.getSession().setAttribute("code",securityCode);
    }
//    安全登出
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }
//    用户展示
    @RequestMapping("showUser")
    @ResponseBody
    public Map<String,Object> showUser(Integer page,Integer rows){
        return userService.findList(page,rows);
    }
    @RequestMapping("set")
    @ResponseBody
    public HashMap<String,Object> updateStatus(String id) {
        HashMap<String, Object> map = userService.updateStatus(id);
        return map;
    }
//    展示所有一级类别
    @RequestMapping("showOneC")
    @ResponseBody
    public Map<String,Object> showCategoryOne(Integer page,Integer rows){
        return categoryService.findAllOne(page,rows);
    }
//    根据一级类别展示所有二级类别
    @RequestMapping("showTwoC")
    @ResponseBody
    public Map<String,Object> showCategoryTwo(Integer page,Integer rows,String id){
        return categoryService.findAllTwoByOne(page,rows,id);
    }

//    修改一级类别
    @RequestMapping("setOneC")
    @ResponseBody
    public HashMap<String,Object> updateOne(String oper,Category category){
        HashMap<String, Object> delete=null;
        if (StringUtils.equals("add",oper)){
            categoryService.insert(category);
        }
        if (StringUtils.equals("edit",oper)){
            categoryService.update(category);
        }
        if (StringUtils.equals("del",oper)){
            delete = categoryService.delete(category);
        }
        return delete;
    }
//   修改二级类别
    @RequestMapping("setTwoC")
    @ResponseBody
    public HashMap<String, Object> updateTwo(String oper,Category category){
        HashMap<String, Object> delete=null;
        if (StringUtils.equals("add",oper)){
            categoryService.insert(category);
        }
        if (StringUtils.equals("edit",oper)){
            categoryService.update(category);
        }
        if (StringUtils.equals("del",oper)){
            delete = categoryService.delete(category);
        }
        return delete;
    }
    @RequestMapping("findNameC")
    public void findNameC(HttpServletResponse response) throws IOException {
        List<Category> allOne = categoryService.findAllOne();
        StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        allOne.forEach(category->{
            sb.append("<option value="+category.getId()+">"+category.getCateName()+"</option>");
            System.out.println("<option value="+category.getId()+">"+category.getCateName()+"</option>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().println(sb.toString());
    }
    @RequestMapping("phoneCode")
    @ResponseBody
    public HashMap<String,Object> phoneCode(String phone, HttpServletRequest request){
        HashMap<String,Object> map= new HashMap<>();
        try {
            String securityCode = ImageCodeUtil.getSecurityCode();
            System.out.println(securityCode);
            String message = AliyunUtil.sendPhoneMsg(phone,securityCode);
            request.getSession().setAttribute("phoneCode",securityCode);
            map.put("status",200);
            map.put("code",securityCode);
        } catch (Exception e) {
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("userExport")
    @ResponseBody
    public String UsersExport(HttpServletRequest request){
        userService.UsersExport(request);
        return "";
    }
    @RequestMapping("userImport")
    @ResponseBody
    public String userImport(HttpServletRequest request){
        userService.UsersImport(request);
        return "";
    }
}
