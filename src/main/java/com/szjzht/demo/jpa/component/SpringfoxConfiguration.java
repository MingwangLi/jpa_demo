package com.szjzht.demo.jpa.component;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.time.LocalDate;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:10
 * @Description:
 */
@Component
@EnableSwagger2
public class SpringfoxConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(createApiInfo()).
                select().
                apis(RequestHandlerSelectors.basePackage("com.szjzht.demo.jpa")).
                paths(Predicates.not(PathSelectors.regex("/error.*"))).
                build().
                pathMapping("/").
                directModelSubstitute(LocalDate.class, String.class).
                genericModelSubstitutes(ResponseEntity.class).
                alternateTypeRules(createAlternateTypeRules()).
                useDefaultResponseMessages(false).
				enableUrlTemplating(false);
    }

    private AlternateTypeRule createAlternateTypeRules() {
        return AlternateTypeRules.newRule(typeResolver.resolve(DeferredResult.class,
                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class));
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder().
                title("Jpa System API").
                description("This document is REST API specification for Jpa System").
                version("1.0.SNAPSHOT").
                build();
    }
}
