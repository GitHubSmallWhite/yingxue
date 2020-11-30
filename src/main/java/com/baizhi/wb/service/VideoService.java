package com.baizhi.wb.service;

import com.baizhi.wb.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface VideoService {
    public String insertOne(Video video);
    public void deleteOne(Video video);
    public HashMap<String,Object> findList(Integer pageNumber, Integer showNumber);
    public String updateOne(Video video);
    public void insertV(MultipartFile file,String uuid);
}
