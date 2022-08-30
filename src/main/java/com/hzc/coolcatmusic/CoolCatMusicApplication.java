package com.hzc.coolcatmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CoolCatMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoolCatMusicApplication.class, args);
    }

}
