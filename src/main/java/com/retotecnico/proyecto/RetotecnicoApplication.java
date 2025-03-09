package com.retotecnico.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.github.jkratz55.mediator.core.Mediator;
import io.github.jkratz55.mediator.core.Registry;
import io.github.jkratz55.mediator.spring.SpringMediator;
import io.github.jkratz55.mediator.spring.SpringRegistry;


@SpringBootApplication(scanBasePackages = {"com.retotecnico.proyecto"})
@EnableJpaRepositories(basePackages = "com.retotecnico.proyecto")
@EntityScan(basePackages = "com.retotecnico.proyecto")
@ComponentScan(basePackages = "com.retotecnico.proyecto")
public class RetotecnicoApplication {

	private final ApplicationContext applicationContext;
    public RetotecnicoApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    Registry registry() {
        return new SpringRegistry(applicationContext);
    }

    @Bean
    Mediator mediator(Registry registry) {
        return new SpringMediator(registry);
    }

    public static void main(String[] args) {
        SpringApplication.run(RetotecnicoApplication.class, args);
    }

}
