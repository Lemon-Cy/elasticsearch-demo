package com.example.elasticsearch.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * Swagger配置类
 * @author Administrator
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket adminDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台接口")
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.elasticsearch.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getParameterList());
    }

    /**
     * 添加全局请求头
     *
     * @return java.util.List<springfox.documentation.service.Parameter>
     * @since 2021/1/27 12:52
     */
    private List<Parameter> getParameterList() {
        //创建全局参数
        return new ArrayList<Parameter>() {{
            add(new ParameterBuilder().required(false)
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .name("Authorization")
                    .description("认证信息")
                    .build());
        }};
    }

    /**
     * 整体说明信息
     *
     * @return ApiInfo
     */
    private ApiInfo getInfo() {
        //创建文档的整体说明对象(作者、描述、标题、版本)
        return new ApiInfoBuilder().contact(new Contact("ChenYu", "", "chenyu@untra.cn"))
                .title("ES")
                .description("ES-Demo")
                .version("1.0")
                .build();
    }
}
