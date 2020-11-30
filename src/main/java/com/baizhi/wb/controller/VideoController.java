package com.baizhi.wb.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.wb.entity.Video;
import com.baizhi.wb.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("video")
public class VideoController {
    @Resource
    private VideoService videoService;
    @RequestMapping("showVideo")
    @ResponseBody
    public HashMap<String,Object> showVideo(Integer page, Integer rows){
        return videoService.findList(page,rows);
    }
    @RequestMapping("setV")
    @ResponseBody
    public String setVideo(String oper, Video video){
        String uuid=null;
        if (StringUtils.equals("add",oper)){
            uuid= videoService.insertOne(video);
        }
        if (StringUtils.equals("edit",oper)){
            uuid = videoService.updateOne(video);
        }
        if (StringUtils.equals("del",oper)){
            videoService.deleteOne(video);
        }
        return uuid;
    }
    @RequestMapping("uploadingV")
    public void uploadingV(MultipartFile videoPath,String id){
          videoService.insertV(videoPath,id);
    }
}
