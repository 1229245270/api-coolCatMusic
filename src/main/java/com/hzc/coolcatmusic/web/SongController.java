package com.hzc.coolcatmusic.web;

import com.hzc.coolcatmusic.bean.Result;
import com.hzc.coolcatmusic.bean.Song;
import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.SongMapper;
import com.hzc.coolcatmusic.mapper.UserMapper;
import com.hzc.coolcatmusic.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/song")
public class SongController {

    private SongMapper songMapper;

    private UploadService uploadService;

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Autowired
    public void setSongMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    @GetMapping("/topNewSong")
    public List<Song> topNewSong(){
        return songMapper.findTopSongByCreateDate(3);
    }

    @GetMapping("/topHotSong")
    public List<Song> topHotSong(){
        return songMapper.findTopSongByPlayTimes(3);
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
                song.setDisplayName(displayName);
                song.setSongName(songName);
                song.setSingerName(singerName);
                song.setPath(uploadPath);
                song.setSongImage(uploadSongImage);
                song.setSingerImage(uploadSingerImage);
                song.setReleaseTime(releaseTime);
                song.setPlayTimes(playTimes);
                int insert = songMapper.insertSong(song);
                if(insert > 0){
                    return Result.success("上传成功");
                }
            }
            return Result.error("上传失败");
    }
}
