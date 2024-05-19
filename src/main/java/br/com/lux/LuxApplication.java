package br.com.lux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LuxApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LuxApplication.class, args);
	}
}
