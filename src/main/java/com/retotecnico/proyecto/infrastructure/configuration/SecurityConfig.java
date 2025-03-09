package com.retotecnico.proyecto.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String moduleName = "/cuentas/**";
        String moduleClientes = "/clientes/**";
        String moduleMovimientos = "/movimientos/**";
        String swaggerPage = "/swagger-ui.html#/**";
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                		auth -> auth
                		.requestMatchers(HttpMethod.PUT,
                                moduleName,moduleClientes,moduleMovimientos,swaggerPage)
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,
                                moduleName,moduleClientes,moduleMovimientos,swaggerPage)
                        .permitAll()
                        .requestMatchers(HttpMethod.OPTIONS,
                                moduleName,moduleClientes,moduleMovimientos)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,
                                moduleName,moduleClientes,moduleMovimientos,swaggerPage)
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE,
                                moduleName,moduleClientes,moduleMovimientos,swaggerPage)
                        .permitAll()
                        .requestMatchers("/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources/**",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/webjars/**").permitAll()
                        .anyRequest()
                        .authenticated()
                        )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.contentSecurityPolicy("script-src 'self'"));
        return http.build();
                
    }
}
