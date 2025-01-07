package cn.qdd.foods.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 东方曜
 * @date 2020/8/28 17:45
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization")
//                .defaultValue("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJlY2hpc2FuIiwic3ViIjoie1wibG9naW5OYW1lXCI6XCJsaXhpYW9cIixcInBhc3N3b3JkXCI6XCJsaXhpYW9cIn0iLCJpYXQiOjE1OTk1NTk0MjEsImV4cCI6MTYwNTYwNzQyMX0.zb8GN6EmaeYbpdMIqUIE6VM8fQqPBvwxHBXAJZfqwXgTXp5mJ8Ip7dTwuUpjPO1f4R0Qw1IEmCsyS__8E8DPdQ")
                .description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build().globalOperationParameters(pars);


    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("video cms API Doc")
                .description("This is a restful api document of Spring Boot.")
                .version("1.0")
                .build();
    }


}
