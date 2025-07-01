package com.fajar.entrypoint.kafka;

import com.fajar.domain.model.UserData;
import com.fajar.usecase.SaveUserDataUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaConsumerHandler {

    @Inject
    SaveUserDataUseCase useCase;

    @Incoming("user-data-in")
    public void consume(String message) {
        String[] parts = message.split(",");
        if (parts.length != 2) return;

        String name = parts[0].trim().toUpperCase();
        int age = Integer.parseInt(parts[1].trim());

        UserData user = new UserData(name, age);
        useCase.execute(user);
    }
}
