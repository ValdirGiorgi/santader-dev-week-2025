package me.dio.teste.santader_dev_week_2025;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages = "me.dio.teste")
@EntityScan("me.dio.teste.domain.model")
@EnableJpaRepositories("me.dio.teste.domain.repository")
@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL") })
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		logger.info("Iniciando a aplicação Santander Dev Week 2025");
		SpringApplication.run(Application.class, args);
		logger.info("Aplicação iniciada com sucesso");
	}

}
