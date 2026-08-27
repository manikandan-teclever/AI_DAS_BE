package com.teclever.aidas.kafka;

import com.teclever.aidas.mongo.document.EventDocument;
import com.teclever.aidas.mongo.repository.EventDocumentRepository;
import com.teclever.aidas.web.EventDto;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    private final EventDocumentRepository repository;

    public EventConsumer(EventDocumentRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${app.kafka.events-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onEvent(EventDto event) {
        log.info("Archiving event {} of type {}", event.id(), event.type());
        repository.save(EventDocument.builder()
                .eventId(event.id())
                .type(event.type())
                .payload(event.payload())
                .receivedAt(Instant.now())
                .build());
    }
}
