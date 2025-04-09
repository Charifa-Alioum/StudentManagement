package com.selftest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeHttpRequests(auth ->
		auth.requestMatchers(HttpMethod.GET, "/students").hasAuthority("SCOPE_read:students")
		.requestMatchers(HttpMethod.POST, "/students").hasAuthority("SCOPE_write:students")
		.requestMatchers(HttpMethod.PUT, "/students/*").hasAuthority("SCOPE_write:students")
		.requestMatchers(HttpMethod.DELETE, "/students/*").hasAuthority("SCOPE_delete:students")
		.anyRequest().authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		
		return httpSecurity.build();
	}
	
}
