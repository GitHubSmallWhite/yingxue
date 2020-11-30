package com.baizhi.wb.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CreateBucketRequest;

import java.util.List;

public class OssUtil {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";// // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static  String accessKeyId = "LTAI4GBVHUg5Htb4vq1wiE6J";//阿里云AccessKeyId
    private static  String accessKeySecret = "e8SJx9tmraTvvIQyu94tQWOF35ZJ7l";//阿里云AccessKeySecret
    //创建储存空间
    public static void createBucket(String bucketName){ //存储空间名
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
        // 此处以设置存储空间的存储类型为标准存储为例。
        // createBucketRequest.setStorageClass(StorageClass.Standard);
        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)
        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    //查询所有储存空间
    public static void queryBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    //删除储存空间
    public void deleteBucket(String bucketName){//储存空间名
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除存储空间。
        ossClient.deleteBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
