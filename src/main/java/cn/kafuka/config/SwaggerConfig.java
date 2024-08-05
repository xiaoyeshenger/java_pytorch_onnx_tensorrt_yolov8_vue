package cn.kafuka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.kafuka.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo())
                .enable(true);
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Interface Api Document")
                .description("This is a api document for Android/browser interface.")
                .license("")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("zhangyong","http://kafuka.com/", "630315438@qq.com"))
                .build();
    }

}
