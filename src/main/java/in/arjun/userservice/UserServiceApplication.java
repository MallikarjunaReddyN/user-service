package in.arjun.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Service", description = "This is a Spring Boot User Application"))
/*@OpenAPIDefinition(info = @Info(title = "User Service", description = "This is a Spring Boot User Application"),
        security = @SecurityRequirement(name = "AUTHORIZATION"))
@SecurityScheme(name = "AUTHORIZATION", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)*/
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
