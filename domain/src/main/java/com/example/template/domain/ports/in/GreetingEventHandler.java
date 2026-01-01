package com.example.template.domain.ports.in;

import com.example.template.domain.events.GreetingCreatedEvent;

public interface GreetingEventHandler {
  void handle(GreetingCreatedEvent event);
}
