package com.datmodder.datannouncements.announcements;

import com.datmodder.datannouncements.enums.Events;
import com.datmodder.datannouncements.enums.MessageTarget;

public class EventAnnouncement {
    public String message;
    public Events event;
    public MessageTarget target;

    public EventAnnouncement() {};

    public EventAnnouncement(String message, Events event, MessageTarget target) {
        this.message = message;
        this.event = event;
        this.target = target;
    }
}
