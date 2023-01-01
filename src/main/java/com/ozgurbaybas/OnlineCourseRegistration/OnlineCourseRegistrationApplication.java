package com.ozgurbaybas.OnlineCourseRegistration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineCourseRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCourseRegistrationApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("Online Course Registration")
						.version(appVersion)
						.description(appDesciption)
						.license(new License().name("Online Course Registration")));
	}
}
