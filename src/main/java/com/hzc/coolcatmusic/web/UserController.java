package com.hzc.coolcatmusic.web;

import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    //static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long,User>());

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public List<User> getUserList(){
        //List<User> userList = new ArrayList<User>(userMap.values());
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

}
