package com.mogreene.backend.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @name : CorsConfig
 * @author : Mo-Greene
 * @date : 2023/06/25
 * Cors Configuration
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Cors 모든 경로, 헤더 허용
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
