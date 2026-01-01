package com.example.template.domain.ports.out;

import com.example.template.domain.Greeting;
import java.util.Optional;
import java.util.UUID;

public interface GreetingCache {
  Optional<Greeting> get(UUID id);

  void put(Greeting greeting);

  void evict(UUID id);

  void evictAll();
}
