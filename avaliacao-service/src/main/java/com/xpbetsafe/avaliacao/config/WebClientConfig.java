package com.xpbetsafe.avaliacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  WebClient apostasClient(@Value("${clients.apostas.base-url}") String baseUrl) {
    return WebClient.builder().baseUrl(baseUrl).build();
  }
}
