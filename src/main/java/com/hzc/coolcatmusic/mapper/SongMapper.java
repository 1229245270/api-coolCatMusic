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

    @Select("SELECT top #{number} * FROM SONG order by create_date desc")
    List<Song> findTopSongByCreateDate(@Param("number") int number);

    @Select("SELECT top #{number} * FROM SONG order by play_times desc")
    List<Song> findTopSongByPlayTimes(@Param("number") int number);

    @Insert("INSERT INTO Song(display_name,song_name,singer_name,path,song_image,singer_image,release_time,play_times) " +
            "VALUES(#{displayName},#{songName},#{singerName},#{path},#{songImage},#{singerImage},#{releaseTime},#{playTimes})")
    int insertSong(Song song);
}
