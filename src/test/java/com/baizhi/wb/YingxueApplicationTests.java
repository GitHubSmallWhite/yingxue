package com.baizhi.wb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class YingxueApplicationTests {
//    @Resource
//    private CategoryMapper categoryMapper;
//    @Test
//    void contextLoads() {
//        CategoryExample categoryExample = new CategoryExample();
//        categoryExample.createCriteria().andLevelsEqualTo(1);
//        List<Category> categories = categoryMapper.selectByExample(categoryExample);
//        categories.forEach(category -> System.out.println(category));
//    }
//    @Test
//    public void createBucket(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4GBVHUg5Htb4vq1wiE6J";
//        String accessKeySecret = "e8SJx9tmraTvvIQyu94tQWOF35ZJ7l";
//        String bucketName = "yx-zp-2005";  //存储空间名
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 创建CreateBucketRequest对象。
//        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//
//        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
//        // 此处以设置存储空间的存储类型为标准存储为例。
//        // createBucketRequest.setStorageClass(StorageClass.Standard);
//        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
//        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)
//
//        // 创建存储空间。
//        ossClient.createBucket(createBucketRequest);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
//    @Test
//    public void queryBucket(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4GBVHUg5Htb4vq1wiE6J";
//        String accessKeySecret = "e8SJx9tmraTvvIQyu94tQWOF35ZJ7l";
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 列举存储空间。
//        List<Bucket> buckets = ossClient.listBuckets();
//        for (Bucket bucket : buckets) {
//            System.out.println(" - " + bucket.getName());
//        }
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }

    @Test
    void video() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\IDEA\\works\\yingxue\\src\\main\\webapp\\img\\2.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\a.jpg");
        while (true){
            int read = fileInputStream.read();
            if (read==-1)break;
            fileOutputStream.write(read);
        }
    }
}
