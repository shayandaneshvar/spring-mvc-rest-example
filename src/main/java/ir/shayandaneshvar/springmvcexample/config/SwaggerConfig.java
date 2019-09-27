package ir.shayandaneshvar.springmvcexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig /*extends WebMvcConfigurationSupport*/ {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
                .pathMapping("/").apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Shayan", "shayandaneshvar.ir", "daneshvarshayan" +
                "@gmail.com");
        return new ApiInfo("Rest Example", "", "1.0", "...", contact,
                "Apache License V2.0", "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

    //required for non spring-boot applications in order to configure swagger-ui
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
//                "classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath" +
//                ":/META-INF/resources/webjars/");
//    }
}
