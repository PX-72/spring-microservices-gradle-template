package com.example.template.domain.ports.out;

import com.example.template.domain.events.GreetingCreatedEvent;

public interface GreetingEventPublisher {
  void publish(GreetingCreatedEvent event);
}
