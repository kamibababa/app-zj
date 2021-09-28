package com.cxy.zjbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.manager.UploadImageService;
import com.cxy.zjbg.util.StringUtil;
import com.qiniu.http.Response;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/16 1:53 下午
 */

@Slf4j
@RestController
@RequestMapping("/oss")
public class UploadController {

    @Autowired
    private UploadImageService uploadImageService;

    @PostMapping(value = "/upload")
    private Result upLoadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取文件的名称
        String fileName = file.getOriginalFilename();
        // 使用工具类根据上传文件生成唯一图片名称
        String imgName = StringUtil.getRandomImgName(fileName);
        if (!file.isEmpty()) {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            String path = uploadImageService.uploadQNImg(inputStream, imgName);
            System.out.print("七牛云返回的图片链接:" + path);
            return Result.succ(path);
        }
        return Result.fail("上传失败");
    }

    @PostMapping(value = "/delete")
    public Result deleteImage(@RequestBody JSONObject key){
        String keys=key.getString("key");
        if(!key.equals("")){
            keys = keys.substring(25);
            String s = uploadImageService.deleteQNImg(keys);
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

}
