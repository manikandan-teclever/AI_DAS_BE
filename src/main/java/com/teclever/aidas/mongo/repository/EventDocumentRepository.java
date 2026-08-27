package com.teclever.aidas.mongo.repository;

import com.teclever.aidas.mongo.document.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDocumentRepository extends MongoRepository<EventDocument, String> {
}
