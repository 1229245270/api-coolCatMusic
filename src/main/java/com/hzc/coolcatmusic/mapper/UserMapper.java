package com.hzc.coolcatmusic.mapper;

import com.hzc.coolcatmusic.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE account = #{account}")
    User findByAccount(@Param("account") String account);

    @Select("SELECT * FROM USER WHERE ID = #{ID}")
    User findById(@Param("id") Long id);

    @Select("SELECT * FROM USER WHERE account = #{account} and password = #{password}")
    User checkUser(@Param("account") String account,@Param("password") String password);

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
