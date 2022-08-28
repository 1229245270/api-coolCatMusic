package com.hzc.coolcatmusic.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    private Date createData;
    private String DisplayName;
    private String songName;
    private String singerName;
    private String path;
    private String songImage;
    private String singerImage;
    private String releaseTime;
    private String playTimes;
}
