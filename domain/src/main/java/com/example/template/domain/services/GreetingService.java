package com.example.template.domain.services;

import com.example.template.domain.Greeting;
import java.util.Optional;
import java.util.UUID;

public interface GreetingService {
  Greeting createGreeting(String name);

  Optional<Greeting> getGreeting(UUID id);
}
