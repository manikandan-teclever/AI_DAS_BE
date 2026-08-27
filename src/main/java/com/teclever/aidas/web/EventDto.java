package com.teclever.aidas.web;

import com.teclever.aidas.mysql.entity.EventEntity;
import java.time.Instant;

public record EventDto(Long id, String type, String payload, Instant createdAt) {

    public static EventDto from(EventEntity entity) {
        return new EventDto(entity.getId(), entity.getType(), entity.getPayload(), entity.getCreatedAt());
    }
}
