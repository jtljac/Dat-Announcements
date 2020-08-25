package com.datmodder.datannouncements.delayedevents;

import com.datmodder.datannouncements.Util;
import com.demmodders.datmoddingapi.delayedexecution.delayedevents.BaseDelayedEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;

public class DelayedAnnouncementEvent extends BaseDelayedEvent {

    final int fullDelay;
    ITextComponent message;
    boolean cancelled = false;

    public DelayedAnnouncementEvent(int Delay, int FullDelay, String Message) {
        super(Delay);
        fullDelay = FullDelay;
        message = ForgeHooks.newChatWithLinks(Message);
    }

    @Override
    public void execute() {
        Util.sendMessage(message);

        exeTime = System.currentTimeMillis() + (((long) fullDelay) * 1000);
    }

    @Override
    public boolean shouldRequeue(boolean hasFinished) {
        return !cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}
