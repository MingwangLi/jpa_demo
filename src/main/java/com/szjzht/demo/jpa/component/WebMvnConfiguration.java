package com.szjzht.demo.jpa.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:01
 * @Description:
 */
@Component
public class WebMvnConfiguration extends WebMvcConfigurationSupport {


    /**
     * Swagger2
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger-resources/**");
        registry.addResourceHandler("/v2/**");
    }
}
