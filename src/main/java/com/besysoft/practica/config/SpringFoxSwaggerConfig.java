package com.besysoft.practica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {

     @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.besysoft.practica.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

   private ApiInfo apiInfo(){
        return new ApiInfo(
                "Bootcamp de Spring Boot",
                "API para el uso de BesySoft",
                "V1",
                "URL de termino y servicios",
                new Contact("Sofia Torres", "https://www.linkedin.com/in/sof%C3%ADa-elizabeth-torres-web-developer/", "sofia.rrii@gmail.com"),
                "Licencia API",
                "URL LICENCIA API",
                Collections.emptyList()
        );
    }
}
