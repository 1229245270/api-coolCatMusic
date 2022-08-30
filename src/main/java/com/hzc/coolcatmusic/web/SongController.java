package com.hzc.coolcatmusic.web;

import com.hzc.coolcatmusic.bean.Result;
import com.hzc.coolcatmusic.bean.Song;
import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.SongMapper;
import com.hzc.coolcatmusic.mapper.UserMapper;
import com.hzc.coolcatmusic.service.UploadService;
import com.hzc.coolcatmusic.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/song")
public class SongController {

    private SongMapper songMapper;

    private UploadService uploadService;

    private final String ip = ServiceUtil.getUrl();

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Autowired
    public void setSongMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    @GetMapping("/newSong")
    public List<Song> topNewSong(int page,int size){
        List<Song> list = songMapper.findTopSongByCreateDate((1 - page) * size,size);
        return addIp(list);
    }

    @GetMapping("/hotSong")
    public List<Song> topHotSong(int page,int size){
        List<Song> list = songMapper.findTopSongByPlayTimes((1 - page) * size,size);
        return addIp(list);
    }

    @GetMapping("/homeSong")
    public Map<String,Object> homeSong(){
        Map<String,Object> map = new HashMap<>();
        List<Song> hotList = songMapper.findTopSongByPlayTimes(0,5);
        map.put("hotList",addIp(hotList));
        List<Song> newList = songMapper.findTopSongByCreateDate(0,5);
        map.put("newList",addIp(newList));
        return map;
    }

    private List<Song> addIp(List<Song> list){
        List<Song> newList = new ArrayList<>();
        for(Song song : list){
            song.setPath(ip + song.getPath());
            song.setSong_image(ip + song.getSong_image());
            song.setSinger_image(ip + song.getSinger_image());
            newList.add(song);
        }
        return newList;
    }

    @PostMapping("/updateSong")
    public Result<String> postSong(
        String displayName,
        String songName,
        String singerName,
        MultipartFile path,
        MultipartFile songImage,
        MultipartFile singerImage,
        String releaseTime,
        String playTimes){
            if(path == null || songImage == null || singerImage == null){
                return Result.error("上传失败");
            }
            String uploadPath = uploadService.uploadImg(path);
            String uploadSongImage = uploadService.uploadImg(songImage);
            String uploadSingerImage = uploadService.uploadImg(singerImage);
            if(uploadPath != null && uploadSongImage != null && uploadSingerImage != null){
                Song song = new Song();
                song.setDisplay_name(displayName);
                song.setSong_name(songName);
                song.setSinger_name(singerName);
                song.setPath(uploadPath);
                song.setSong_image(uploadSongImage);
                song.setSinger_image(uploadSingerImage);
                song.setRelease_time(releaseTime);
                song.setPlay_times(playTimes);
                long nowTime = System.currentTimeMillis();
                song.setCreate_date(nowTime);
                song.setUpdate_date(nowTime);
                int insert = songMapper.insertSong(song);
                if(insert > 0){
                    return Result.success("上传成功");
                }
            }
            return Result.error("上传失败");
    }

    @PostMapping("/kgm")
    public void kgm(){
        try {
            String path = System.getProperty("user.dir");
            Runtime mt = Runtime.getRuntime();
            String cmd = path + "\\exe\\kgm-decoder.exe F:\\kugoumusic";
            Process pro = mt.exec(cmd);
            InputStream ers = pro.getErrorStream();
            pro.waitFor();
        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        } // TODO Auto-generated catch block

    }
}
