package com.cxy.zjbg.manager;

import com.cxy.zjbg.config.CloudStorageConfig;

import java.io.FileInputStream;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/16 1:50 下午
 */
public abstract class UploadImageService {

    protected CloudStorageConfig config;

    public abstract String uploadQNImg(FileInputStream file, String path);

    public abstract String deleteQNImg(String key);
}
