package com.desafio.trinity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private Docket docket = new Docket(DocumentationType.SWAGGER_2);

    @Bean
    public Docket detail() {
        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafio.trinity"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.info().build());

        return docket;
    }

    private ApiInfoBuilder info() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api RESTful para Sistema de Usuários de Carros");
        apiInfoBuilder.version("1.0");

        return apiInfoBuilder;
    }
}
