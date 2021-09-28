package com.cxy.zjbg.manager;


import com.cxy.zjbg.config.CloudStorageConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/16 1:51 下午
 */
@Service
public class UploadImageServiceImpl extends UploadImageService{
    // 七牛文件上传管理器
    private UploadManager uploadManager;
    //七牛文件下载管理器
    private BucketManager bucketManager;

    private String token;
    // 七牛认证管理
    private Auth auth;

    public UploadImageServiceImpl(CloudStorageConfig config){
        this.config = config;
        init();
    }

    private void init(){
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        // 根据命名空间生成的上传token
        token = auth.uploadToken(config.getQiniuBucketName());
        System.out.println(token);
        uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        bucketManager=new BucketManager(auth,new Configuration(Zone.zone0()));
    }

    @Override
    public String uploadQNImg(FileInputStream file, String key) {
        try{
            // 上传图片文件
            token = auth.uploadToken(config.getQiniuBucketName());
            Response res = uploadManager.put(file, key, token, null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);

            String path = config.getQiniuDomain() + "/" + putRet.key;
            // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
            return path;
        }catch (QiniuException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String deleteQNImg(String key) {
        try {
            token = auth.uploadToken(config.getQiniuBucketName());
            Response delete = bucketManager.delete(config.getQiniuBucketName(), key);
            return "success";
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        return "";
    }
}
