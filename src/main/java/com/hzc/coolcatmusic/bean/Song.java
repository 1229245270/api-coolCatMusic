package com.hzc.coolcatmusic.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreatedDate
    private Date createData;
    private String displayName;
    private String songName;
    private String singerName;
    private String path;
    private String songImage;
    private String singerImage;
    private String releaseTime;
    private String playTimes;
}
