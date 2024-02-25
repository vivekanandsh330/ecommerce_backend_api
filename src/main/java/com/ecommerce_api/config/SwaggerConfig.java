package com.ecommerce_api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /*
     * public static final Contact DEFAULT_CONTACT = new Contact( "Ranga Karanam",
     * "http://www.in28minutes.com", "in28minutes@gmail.com");
     *
     * public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
     * "Awesome API Title", "Awesome API Description", "1.0", "urn:tos",
     * DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
     * private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new
     * HashSet<String>(Arrays.asList("application/json", "application/xml"));
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.ecommerce_api.controller"))
                .build()
                .apiInfo(apiDetails());
        //return new Docket(DocumentationType.SWAGGER_2);
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Users API",
                "Sample API for Testing",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Vivekanand Singh", "http://github.com/vivekanandsh330", "vivekanandsh330@gmail.com"),
                "API License",
                "http://willupdate.com",
                Collections.emptyList());
    }
}