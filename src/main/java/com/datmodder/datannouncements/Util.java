package com.datmodder.datannouncements;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Util {
    public static void sendMessage(ITextComponent Message) {
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(Message);
    }

    public static void sendMessage(ITextComponent Message, EntityPlayerMP Player, boolean Exclusive) {
        if (Exclusive) {
            for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
                if (!player.equals(Player)) {
                    player.sendMessage(Message);
                }
            }
        } else {
            Player.sendMessage(Message);
        }
    }
}
