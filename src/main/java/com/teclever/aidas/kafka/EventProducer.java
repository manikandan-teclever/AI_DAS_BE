package com.teclever.aidas.kafka;

import com.teclever.aidas.web.EventDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    private final String topic;

    public EventProducer(KafkaTemplate<String, EventDto> kafkaTemplate,
                         @Value("${app.kafka.events-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(EventDto event) {
        kafkaTemplate.send(topic, String.valueOf(event.id()), event);
    }
}
