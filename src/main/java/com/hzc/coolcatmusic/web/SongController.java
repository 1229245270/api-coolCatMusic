package com.hzc.coolcatmusic.web;

import com.hzc.coolcatmusic.bean.Song;
import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.SongMapper;
import com.hzc.coolcatmusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/song")
public class SongController {

    private SongMapper songMapper;

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

    @PostMapping("/insertSong")
    public String postSong(@RequestBody Song song){
        //songMapper.(user);
        return "success";
    }
}
