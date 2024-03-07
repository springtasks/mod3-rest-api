package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("example.service.impl")
@EntityScan("com.example.dto")
@EnableJpaRepositories("example.repository")
//@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); // Completely Optional
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Task 3 Rest APIs")
                .description("Demonstration of Rest API")
                .version("1.0")
                .build();
    }*/

}
