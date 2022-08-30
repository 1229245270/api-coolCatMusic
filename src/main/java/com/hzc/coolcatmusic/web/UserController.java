package com.hzc.coolcatmusic.web;

import com.hzc.coolcatmusic.bean.Result;
import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.UserMapper;
import com.hzc.coolcatmusic.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public List<User> getUserList(){
        return userMapper.findAll();
    }

    @PostMapping("/")
    public String postUser(@RequestBody User user){
        userMapper.insertByUser(user);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userMapper.findById(id);
    }

    @PutMapping("/")
    public String putString(@RequestBody User user){
        userMapper.update(user);
        return "success";
    }

    @PostMapping("login")
    public Result<String> login(String account, String password){
        User user = userMapper.checkUser(account, password);
        if(user != null){
            String token = JwtUtil.sign(account, password);
            if (token != null) {
                return Result.success(token);
            }
        }
        return Result.error("错误");
    }

    @GetMapping("userInfo")
    public Object userInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        String verity = JwtUtil.verity(token);
        if(verity != null){
            return userMapper.findByAccount(verity);
        }
        return Result.error(null);
    }
}
