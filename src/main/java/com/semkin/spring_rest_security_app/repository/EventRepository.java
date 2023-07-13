package com.semkin.spring_rest_security_app.repository;

import com.semkin.spring_rest_security_app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
