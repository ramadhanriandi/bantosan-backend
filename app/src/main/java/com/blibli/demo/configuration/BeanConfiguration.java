package com.blibli.demo.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class BeanConfiguration {

	@Value(value = "${demo.api.default.timeout}")
	private Integer defaultTimeout;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) throws Exception {
		restTemplateBuilder.setConnectTimeout(Duration.ofMillis(this.defaultTimeout));
		restTemplateBuilder.setReadTimeout(Duration.ofMillis(this.defaultTimeout));
		return restTemplateBuilder.build();
	}
}
