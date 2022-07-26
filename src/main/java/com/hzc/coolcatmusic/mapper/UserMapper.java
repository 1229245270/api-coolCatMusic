package com.hzc.coolcatmusic.mapper;

import com.hzc.coolcatmusic.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME = #{NAME}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM USER WHERE ID = #{ID}")
    User findById(@Param("id") Long id);


    @Insert("INSERT INTO USER(NAME,AGE) VALUES(#{{name},#{age})")
    int insert(@Param("name") String name,@Param("age") Integer age);

    @Insert("INSERT INTO USER(NAME,AGE) VALUES(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("INSERT INTO USER(NAME,AGE) VALUES(#{{name},#{age})")
    int insertByUser(User user);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    /*@Results({
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age")
    })*/
    @Select("SELECT * FROM user")
    List<User> findAll();
}
