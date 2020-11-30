package com.baizhi.wb.service;

import com.baizhi.wb.annotcation.AddLog;
import com.baizhi.wb.annotcation.AddRedis;
import com.baizhi.wb.annotcation.DelRedis;
import com.baizhi.wb.dao.UserMapper;
import com.baizhi.wb.entity.User;
import com.baizhi.wb.entity.UserExample;
import com.baizhi.wb.util.ExcelUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddRedis
    @Override
    public HashMap<String,Object> findList(Integer pageNumber, Integer showNumber) {
        int count = userMapper.selectCount(new User());//总数
        if (pageNumber==null){
            pageNumber=1;
        }
        UserExample example = new UserExample();
        example.setOrderByClause("create_date asc");
        RowBounds rowBounds = new RowBounds((pageNumber-1)*showNumber,showNumber);
        Integer total=count%showNumber==0?count/showNumber:count/showNumber+1;
        List<User> users = userMapper.selectByExampleAndRowBounds(example,rowBounds);
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",pageNumber);//当前页码
        map.put("total",total);//总页数
        map.put("records",count);//总条数
        map.put("rows",users);//单页显示的数据
        return map;
    }
    @AddLog("修改用户状态")
    @DelRedis
    @Override
    public HashMap<String,Object> updateStatus(String id) {
        HashMap<String,Object> map=new HashMap<>();
        //拿取修改的字段
        User user=userMapper.selectByPrimaryKey(id);
        User user1 = new User();
        user1.setId(id);
        //判断当前状态来决定修改状态
        if (user.getStatus()==0){
            user1.setStatus(1);
        }else {
            user1.setStatus(0);
        }
        userMapper.updateByPrimaryKeySelective(user1);
        map.put("id",id);
        map.put("status",user1.getStatus());
        return map;
    }
    @AddLog("导出用户信息")
    @Override
    public void UsersExport(HttpServletRequest request){
        List<User> users = userMapper.selectAll();
        try {
            ExcelUtil.ExcelExport(users,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AddLog("导入用户信息")
    @DelRedis
    @Override
    public void UsersImport(HttpServletRequest request) {
        List<User> users = ExcelUtil.ExcelImport();
        String realPath = request.getServletContext().getRealPath("/img");

        System.out.println("上传路径"+realPath);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("读入数据"+user);

            File file = new File(user.getPicImg());
            System.out.println("图片路径"+file.getAbsolutePath());
            String name = file.getName();
//            FileInputStream inputStream=null;
//            FileOutputStream outputStream=null;
//            try {
//                FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
//                FileOutputStream outputStream = new FileOutputStream(realPath);
//                while (true){
//                    try {
//                        int read = inputStream.read();
//                        while (read==-1)break;
//                        outputStream.write(read);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            user.setPicImg("img/"+name);
            userMapper.insert(user);
        }
    }
}

