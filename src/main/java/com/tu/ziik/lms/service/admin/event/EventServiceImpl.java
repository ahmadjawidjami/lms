package com.tu.ziik.lms.service.admin.event;

import com.tu.ziik.lms.model.Event;
import com.tu.ziik.lms.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ahmadjawid on 1/29/17.
 */

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void saveEvent(Event event) {

        eventRepository.save(event);

    }

    @Override
    public Object findAllEvents() {
        return eventRepository.findAll();
    }
}
