package com.hzc.coolcatmusic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;

    // MultipartFile   这个对象是SpringMvc提供的文件上传的请求的类
    // 它的底层会自动和httpServletRequest request 中的request.getInputStream() 整合  从而达到文件上传的目的
    //   dir 指定上传的目录
    //  文件上传底层的原理是 request.getInputStream()
    public String uploadImg(MultipartFile file){

        try {
            // 截取文件名 的后缀 防止出现文件名的重复 覆盖
            String realFilename = file.getOriginalFilename();
            String suffix = "";
            if(realFilename != null){
                suffix = realFilename.substring(realFilename.lastIndexOf("."));
            }
            //将其生成唯一的文件名
            String newFilename = System.currentTimeMillis() + suffix;

            //日期目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
            String datePath  = dateFormat.format(new Date());

            // 指定文件上传的目录   生成一个最终的目录
            File targetFile = new File(uploadFolder + datePath);
            if(!targetFile.exists()) {
                targetFile.mkdirs();
            }

            // 指定文件上传以后服务器完整的文件名
            File targetFilename = new File(targetFile,newFilename);
            //文件上传
            file.transferTo(targetFilename);
            return staticPatternPath.replace("*","") + datePath + newFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
