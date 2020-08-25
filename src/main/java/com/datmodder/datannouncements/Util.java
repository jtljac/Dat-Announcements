package com.datmodder.datannouncements;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Util {
    public static void sendMessage(String Message) {
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(ForgeHooks.newChatWithLinks(Message));
    }

    public static void sendMessage(String Message, EntityPlayerMP Player, boolean Exclusive) {
        if (Exclusive) {
            for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
                if (!player.equals(Player)) {
                    player.sendMessage(ForgeHooks.newChatWithLinks(Message));
                }
            }
        } else {
            Player.sendMessage(ForgeHooks.newChatWithLinks(Message));
        }
    }
}
