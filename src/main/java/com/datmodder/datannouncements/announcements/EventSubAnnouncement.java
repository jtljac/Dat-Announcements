package com.datmodder.datannouncements.announcements;

import com.datmodder.datannouncements.enums.MessageTarget;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;

public class EventSubAnnouncement {
    public ITextComponent message;
    public MessageTarget target;

    public EventSubAnnouncement(String Message, MessageTarget Target) {
        message = ForgeHooks.newChatWithLinks(Message);
        target = Target;
    }
}
