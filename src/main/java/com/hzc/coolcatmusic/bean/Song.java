package com.hzc.coolcatmusic.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Date createData;
    @LastModifiedDate
    private Date updateDate;
    private String displayName;
    private String songName;
    private String singerName;
    private String path;
    private String songImage;
    private String singerImage;
    private String releaseTime;
    private String playTimes;
}
