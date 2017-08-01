package com.jk.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * Created by JK on 2017/3/9.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger摘要bean
     * @return
     */
    @Bean
    public Docket restApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jk.modules.api"))//过滤的接口
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * API文档主信息对象
     * @return
     */
    private ApiInfo apiInfo(){
        ApiInfo apiInfo= (new ApiInfoBuilder())
                .title("Good相关接口文档V1")  ////大标题
//                .description("xxx") ////详细描述
//                .termsOfServiceUrl("NO terms of service")
//                .contact(new Contact("cent","","jk@invest.cn"))//作者
                .version("1.0") //版本
                .build();
        return apiInfo;
    }
}
