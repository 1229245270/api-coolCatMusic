package com.hzc.coolcatmusic.service;

import com.hzc.coolcatmusic.bean.Song;
import com.hzc.coolcatmusic.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;

    @Value("${file.uploadTempSongPath}")
    private String uploadTempSongPath;

    @Value("${file.urlTempSongPath}")
    private String urlTempSongPath;

    @Value("${server.port}")
    private int serverPort;

    public List<Song> addIp(List<Song> list){
        List<Song> newList = new ArrayList<>();
        for(Song song : list){
            song.setPath(ServiceUtil.getUrl(serverPort) + song.getPath());
            song.setSong_image(ServiceUtil.getUrl(serverPort) + song.getSong_image());
            song.setSinger_image(ServiceUtil.getUrl(serverPort) + song.getSinger_image());
            newList.add(song);
        }
        return newList;
    }

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

    public String unlockSong(MultipartFile file,String username){
        try {
            // 截取文件名 的后缀 防止出现文件名的重复 覆盖
            String realFilename = file.getOriginalFilename();
            // 指定文件上传的目录   生成一个最终的目录
            File targetFile = new File(uploadTempSongPath + username + "/");
            if(!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 指定文件上传以后服务器完整的文件名
            File targetFilename = new File(targetFile,realFilename);
            //文件上传到服务器本地
            file.transferTo(targetFilename);
            String path = System.getProperty("user.dir");
            Runtime mt = Runtime.getRuntime();
            String cmd = path + "\\exe\\um-windows-amd64.exe -i " + targetFilename.getPath() + " -o " + targetFile.getPath();
            Process pro = mt.exec(cmd);
            String inStr = consumeInputStream(pro.getInputStream());
            String ers = consumeInputStream(pro.getErrorStream());
            int retCode = pro.waitFor();
            String fileName = realFilename.substring(0,realFilename.lastIndexOf(".")) + ".mp3";
            File mp3File = new File(targetFile,fileName);
            if(mp3File.exists()){
                return ServiceUtil.getUrl(serverPort) + urlTempSongPath.replace("*","") + username + "/" + fileName;
            }
        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        } // TODO Auto-generated catch block
        return "nul";
    }

    public String consumeInputStream(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s ;
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            System.out.println(s);
            sb.append(s);
        }
        return sb.toString();
    }
}
