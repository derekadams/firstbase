package com.firstbase.codetest.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
	ApiInfo info = new ApiInfo("Firstbase Code Test APIs", "API for interacting with Firstbase code test project.",
		"0.0.1", null, null, null, null, Collections.emptyList());
	return new Docket(DocumentationType.SWAGGER_2).apiInfo(info).select()
		.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
		.build();
    }
}
