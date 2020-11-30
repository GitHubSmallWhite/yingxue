package com.baizhi.wb.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class FileUtil {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";// // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static  String accessKeyId = "LTAI4GBVHUg5Htb4vq1wiE6J";//阿里云AccessKeyId
    private static  String accessKeySecret = "e8SJx9tmraTvvIQyu94tQWOF35ZJ7l";//阿里云AccessKeySecret
    private static  String bucketName="yx-wb";
    //上传本地文件
    public static void uploadVideo(byte[] bytes, String objectName) {//参数1 文件名字节数组  参数2 文件名字
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //上传bytes数组
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    public static void deleteVideo(String objectName){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";
        // 创建OSSClient实例。
        String video="video/";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName,video+objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    public static String uplodeFm(String objectName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    // 设置视频截帧操作。
        String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
    // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
        String url=signedUrl.toString();
        // 关闭OSSClient。
        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(objectName);
        String[] split = objectName.split("/");
        String s = split[split.length - 1];
        String[] split1 = s.split("\\.");
        String s1 = split1[0];
        String yourObjectName="product/"+s1+".jpg";
        ossClient.putObject(bucketName, yourObjectName, inputStream);
        ossClient.shutdown();
        return yourObjectName;
    }    public static void deleteFm(String objectName){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";
        // 创建OSSClient实例。
        String video="product/";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName,video+objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
