package com.tu.ziik.lms.service.admin.event;

import com.tu.ziik.lms.model.Event;

/**
 * Created by ahmadjawid on 1/29/17.
 */
public interface EventService {
    void saveEvent(Event event);

    Object findAllEvents();
}
