package com.datmodder.datannouncements.DelayedEvents;

import com.datmodder.datannouncements.Util;
import com.demmodders.datmoddingapi.delayedexecution.delayedevents.BaseDelayedEvent;

public class DelayedAnnouncementEvent extends BaseDelayedEvent {

    final int fullDelay;
    String message;
    boolean cancelled = false;

    public DelayedAnnouncementEvent(int Delay, int FullDelay, String Message) {
        super(Delay);
        fullDelay = FullDelay;
        message = Message;
    }

    @Override
    public void execute() {
        Util.sendMessage(message);

        exeTime = System.currentTimeMillis() + (fullDelay * 1000);
    }

    @Override
    public boolean shouldRequeue(boolean hasFinished) {
        return !cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}
