
package com.example.nimap.PayrollTask.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.springboot.nimap.PayrollTask.springboot.Util.FileStorageProperties;

@SpringBootApplication
@ComponentScan({ "com.example.nimap.PayrollTask.springboot.*" })
@EntityScan("com.example.nimap.PayrollTask.springboot.*")
@EnableJpaRepositories("com.example.nimap.PayrollTask.springboot.Repository" + "")
@EnableConfigurationProperties(FileStorageProperties.class)

public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
