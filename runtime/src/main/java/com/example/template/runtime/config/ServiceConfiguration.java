package com.example.template.runtime.config;

import com.example.template.domain.ports.out.GreetingCache;
import com.example.template.domain.ports.out.GreetingEventPublisher;
import com.example.template.domain.ports.out.GreetingStore;
import com.example.template.domain.services.GreetingService;
import com.example.template.domain.services.GreetingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
  @Bean
  public GreetingService greetingService(
      GreetingStore greetingStore,
      GreetingCache greetingCache,
      GreetingEventPublisher eventPublisher) {
    return new GreetingServiceImpl(greetingStore, greetingCache, eventPublisher);
  }
}
