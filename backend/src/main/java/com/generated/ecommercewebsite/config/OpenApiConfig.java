package com.generated.ecommercewebsite.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("ecommerce-website API")
                .version("1.0.0")
                .description("Build a fully functional, production-ready Clothing E-commerce Web Application (Myntra-like) using: Frontend: Angular (latest version, standalone components, RxJS, best practices) Backend: Spring Boot (Java, REST APIs...")
                .contact(new Contact()
                    .name("API Support")
                    .url("https://example.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
