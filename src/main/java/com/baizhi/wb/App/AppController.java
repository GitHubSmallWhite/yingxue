package com.baizhi.wb.App;

import com.baizhi.wb.commom.CommomResult;
import com.baizhi.wb.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("app")
public class AppController {
    @Resource
    private VideoService videoService;
    @RequestMapping("queryByReleaseTime")
    @ResponseBody
    public Object show(){
        HashMap<String, Object> list = videoService.findList(1, 3);
        if (list!=null){
            return new CommomResult().success("成功",list);
        }else {
            return new CommomResult().no("失败",null);
        }
    }
}
