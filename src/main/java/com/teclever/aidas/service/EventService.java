package com.teclever.aidas.service;

import com.teclever.aidas.kafka.EventProducer;
import com.teclever.aidas.mysql.entity.EventEntity;
import com.teclever.aidas.mysql.repository.EventRepository;
import com.teclever.aidas.web.CreateEventRequest;
import com.teclever.aidas.web.EventDto;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventProducer eventProducer;

    public EventService(EventRepository eventRepository, EventProducer eventProducer) {
        this.eventRepository = eventRepository;
        this.eventProducer = eventProducer;
    }

    @Transactional(readOnly = true)
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(EventDto::from).toList();
    }

    @Transactional
    public EventDto create(CreateEventRequest request) {
        EventEntity saved = eventRepository.save(EventEntity.builder()
                .type(request.type())
                .payload(request.payload())
                .createdAt(Instant.now())
                .build());
        EventDto dto = EventDto.from(saved);
        eventProducer.publish(dto);
        return dto;
    }
}
