package com.hzc.coolcatmusic.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String display_name;
    private String song_name;
    private String singer_name;
    private String path;
    private String song_image;
    private String singer_image;
    private String release_time;
    private String play_times;
    private Long create_date;
    private Long update_date;
}
