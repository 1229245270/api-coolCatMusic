package com.hzc.coolcatmusic.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Font {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String path;
    private String examplePath;

}
