package org.borja.springcloud.msvc.cliente_persona.cliente_persona.services;

import org.borja.springcloud.msvc.cliente_persona.cliente_persona.event.ClienteEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, ClienteEvent> kafkaTemplate;

    @Value("${kafka.topic.cliente}")
    private String clienteTopic;

    public KafkaProducerService(KafkaTemplate<String, ClienteEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClienteEvent(ClienteEvent event) {
        kafkaTemplate.send(clienteTopic, event);
    }
}