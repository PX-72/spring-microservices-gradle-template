package com.example.template.domain.ports.out;

import com.example.template.domain.Greeting;
import java.util.Optional;
import java.util.UUID;

public interface ExternalGreetingClient {
  Optional<Greeting> fetchGreeting(UUID id);

  Greeting createRemoteGreeting(String name);
}
