package com.example.template.domain.ports.out;

import com.example.template.domain.Greeting;
import java.util.Optional;
import java.util.UUID;

public interface GreetingStore {
  void save(Greeting greeting);

  Optional<Greeting> findById(UUID id);
}
