package com.hzc.coolcatmusic.mapper;

import com.hzc.coolcatmusic.bean.Font;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FontMapper {
    @Select("SELECT * FROM FONT")
    List<Font> findAll();
}
