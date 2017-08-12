package com.jirapave.imdb;

import com.jirapave.imdb.common.constant.SpringProfiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).profiles(SpringProfiles.PROD).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.profiles(SpringProfiles.PROD);
        return application.sources(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

