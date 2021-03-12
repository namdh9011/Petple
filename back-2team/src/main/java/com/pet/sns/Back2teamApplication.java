package com.pet.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.pet.sns.property.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileUploadProperties.class })
@EnableJpaAuditing
public class Back2teamApplication {

	public static void main(String[] args) {
		SpringApplication.run(Back2teamApplication.class, args);
	}
}
