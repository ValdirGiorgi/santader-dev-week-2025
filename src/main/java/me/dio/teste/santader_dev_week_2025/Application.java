package me.dio.teste.santader_dev_week_2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "me.dio.teste")
@EntityScan("me.dio.teste.domain.model")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
