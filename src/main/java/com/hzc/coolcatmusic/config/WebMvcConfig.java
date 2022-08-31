package com.hzc.coolcatmusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.urlTempSongPath}")
    private String urlTempSongPath;
    @Value("${file.uploadTempSongPath}")
    private String uploadTempSongPath;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/download/**").addResourceLocations("file:///F:/upload/");
        registry.addResourceHandler(staticPatternPath).addResourceLocations("file:"+uploadFolder);
        registry.addResourceHandler(urlTempSongPath).addResourceLocations("file:"+uploadTempSongPath);
        super.addResourceHandlers(registry);
    }
}
