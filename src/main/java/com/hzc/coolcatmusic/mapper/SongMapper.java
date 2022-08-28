package com.hzc.coolcatmusic.mapper;

import com.hzc.coolcatmusic.bean.Font;
import com.hzc.coolcatmusic.bean.Song;
import com.hzc.coolcatmusic.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper {
    @Select("SELECT * FROM SONG")
    List<Song> findAll();

    @Select("SELECT top #{number} * FROM SONG order by createDate desc")
    List<Song> findTopSongByCreateDate(@Param("number") int number);

    @Select("SELECT top #{number} * FROM SONG order by playTimes desc")
    List<Song> findTopSongByPlayTimes(@Param("number") int number);

    @Insert("INSERT INTO Song(NAME,AGE) VALUES(#{{name},#{age})")
    int insertByUser(Song user);
}
