package com.pro.privacidade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // path pattern da nossa api
                .allowedHeaders("*")// Headers permitidos
                .allowedOrigins("*")// Origins permitidos ex: "http:www.seufront.com"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS",  "HEAD", "TRACE", "CONNECT");// http methods permitidos
    }
}
