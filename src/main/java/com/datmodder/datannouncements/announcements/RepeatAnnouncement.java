package com.datmodder.datannouncements.announcements;

public class RepeatAnnouncement {
    public String message;
    public int firstDelay;
    public int interval;

    public RepeatAnnouncement() {}

    public RepeatAnnouncement(String message, int firstDelay, int interval) {
        this.message = message;
        this.firstDelay = firstDelay;
        this.interval = interval;
    }
}
