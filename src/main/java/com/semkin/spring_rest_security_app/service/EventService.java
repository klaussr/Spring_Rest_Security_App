package com.semkin.spring_rest_security_app.service;

import com.semkin.spring_rest_security_app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventService extends GenericService<Event, Long>{
    Event create(Event event);

    Event update(Event event);

}
