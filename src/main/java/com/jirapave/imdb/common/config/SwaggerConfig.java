package com.jirapave.imdb.common.config;

import com.google.common.base.Predicates;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Imdb")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select();

        // Ignore default /error
        builder.paths(Predicates.not(PathSelectors.regex("/error")));

        return builder.build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Imdb API",
                "REST API for imdb.com",
                "1.0",
                null,
                new Contact("Pavel Jíra", "http://www.imdb.com/interfaces", "pavel.jira@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
