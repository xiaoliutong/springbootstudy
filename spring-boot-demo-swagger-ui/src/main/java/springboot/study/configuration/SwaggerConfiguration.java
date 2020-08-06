package springboot.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    /*
     docket n : 摘要 计事表
           vt ： 给...附加上
     */
    @Bean
    public Docket creatDocket() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(configurationApiInfo()).select().
                apis(RequestHandlerSelectors.basePackage("springboot.study")).
                paths(PathSelectors.any()).build();
    }

    private ApiInfo configurationApiInfo() {
        return new ApiInfoBuilder().title("swagger_ui演示").
                description("spring-boot-demo-swagger-ui").version("1.0").
                build();
    }
}
