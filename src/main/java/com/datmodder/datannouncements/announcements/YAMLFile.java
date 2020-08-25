package com.datmodder.datannouncements.announcements;

import java.util.ArrayList;

public class YAMLFile {
    public ArrayList<RepeatAnnouncement> repeatAnnoucements = new ArrayList<>();
    public ArrayList<EventAnnouncement> eventAnnouncements = new ArrayList<>();

    public boolean isEmpty() {
        return repeatAnnoucements.isEmpty() && eventAnnouncements.isEmpty();
    }
}
