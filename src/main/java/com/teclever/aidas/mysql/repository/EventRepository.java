package com.teclever.aidas.mysql.repository;

import com.teclever.aidas.mysql.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
