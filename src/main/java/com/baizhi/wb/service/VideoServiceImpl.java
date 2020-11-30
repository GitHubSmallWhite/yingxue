package com.baizhi.wb.service;

import com.baizhi.wb.annotcation.AddLog;
import com.baizhi.wb.annotcation.AddRedis;
import com.baizhi.wb.annotcation.DelRedis;
import com.baizhi.wb.dao.VideoMapper;
import com.baizhi.wb.entity.Video;
import com.baizhi.wb.entity.VideoExample;
import com.baizhi.wb.util.FileUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;
    @AddLog("添加视频")
    @DelRedis
    @Override
    public String insertOne(Video video) {
        String uuid = UUID.randomUUID().toString();
        video.setId(uuid);
        video.setUploadTime(new Date());
        video.setStatus(0);
        video.setLikeCount(0);
        video.setPlayCount(0);
        videoMapper.insert(video);
        return uuid;
    }
    @AddLog("删除视频")
    @DelRedis
    @Override
    public void deleteOne(Video video) {
        video  = videoMapper.selectByPrimaryKey(video);
        System.out.println(video);
        String videoPath = video.getVideoPath();
        String[] split = videoPath.split("/");
        String s = split[split.length - 1];
        FileUtil.deleteVideo(s);
        String coverPath = video.getCoverPath();
        String[] split1 = coverPath.split("/");
        String s1=split1[split1.length-1];
        FileUtil.deleteFm(s1);
        videoMapper.delete(video);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddRedis
    @Override
    public HashMap<String,Object> findList(Integer pageNumber, Integer showNumber) {
        HashMap<String,Object> map=new HashMap<>();
        VideoExample videoExample = new VideoExample();
        RowBounds rowBounds = new RowBounds((pageNumber-1)*showNumber,showNumber);
        List<Video> videos = videoMapper.selectByExampleAndRowBounds(videoExample, rowBounds);
        int count = videoMapper.selectCountByExample(videoExample);
        int total=count%showNumber==0?count/showNumber:count/showNumber+1;
        map.put("page",pageNumber);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",videos);
        return map;
    }
    @AddLog("修改视频")
    @DelRedis
    @Override
    public String updateOne(Video video) {
        if(!video.getVideoPath().equals("")){
            Video videoAfter = videoMapper.selectByPrimaryKey(video);
            String videoPath = videoAfter.getVideoPath();
            String[] split = videoPath.split("/");
            String s = split[split.length - 1];
            FileUtil.deleteVideo(s);
            String[] split1 = videoAfter.getCoverPath().split("/");
            String s1=split1[split1.length-1];
            FileUtil.deleteFm(s1);
        }
        videoMapper.updateByPrimaryKeySelective(video);
        return video.getId();
    }
    @AddLog("上传视频")
    @Override
    public void insertV(MultipartFile file,String uuid) {
        byte[] bytes=null;
        String fileName=uuid+"-"+file.getOriginalFilename();
        String objectName="video/"+fileName;
        try {
            bytes=file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtil.uploadVideo(bytes,objectName);
        Video video = new Video();
        VideoExample videoExample = new VideoExample();
        videoExample.createCriteria().andIdEqualTo(uuid);
        video.setVideoPath("https://yx-wb.oss-cn-beijing.aliyuncs.com/"+objectName);
        String s = FileUtil.uplodeFm(objectName);
        video.setCoverPath("https://yx-wb.oss-cn-beijing.aliyuncs.com/"+s);
        videoMapper.updateByExampleSelective(video,videoExample);
    }
}
