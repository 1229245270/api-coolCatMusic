package com.hzc.coolcatmusic.web.setting;

import com.hzc.coolcatmusic.bean.Font;
import com.hzc.coolcatmusic.bean.Result;
import com.hzc.coolcatmusic.bean.User;
import com.hzc.coolcatmusic.mapper.FontMapper;
import com.hzc.coolcatmusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/setting/font")
public class FontController {

    private FontMapper fontMapper;

    @Autowired
    public void setFontMapper(FontMapper fontMapper) {
        this.fontMapper = fontMapper;
    }


    @GetMapping("")
    public List<Font> getAll(){
        //List<User> userList = new ArrayList<User>(userMap.values());
        return fontMapper.findAll();
    }

    @GetMapping("cesi")
    public List<Font> getAll2(){
        throw new RuntimeException("自定义异常");
    }
}
