package com.jirapave.imdb.common.config;

import com.jirapave.imdb.Application;
import com.jirapave.imdb.common.constant.SpringProfiles;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ApplicationDev extends Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).profiles(SpringProfiles.DEV).run(args);
    }
}
