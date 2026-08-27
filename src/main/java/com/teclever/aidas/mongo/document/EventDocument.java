package com.teclever.aidas.mongo.document;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDocument {

    @Id
    private String id;

    private Long eventId;

    private String type;

    private String payload;

    private Instant receivedAt;
}
