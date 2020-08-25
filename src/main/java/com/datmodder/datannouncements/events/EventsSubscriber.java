package com.datmodder.datannouncements.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.GameRuleChangeEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.village.VillageSiegeEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class EventsSubscriber {
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        
    }

    @SubscribeEvent
    public static void playerLeave(PlayerEvent.PlayerLoggedOutEvent event) {

    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {

    }

    @SubscribeEvent
    public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {

    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {

        }
    }

    @SubscribeEvent
    public static void playerAdvancement(AdvancementEvent event) {

    }

    @SubscribeEvent
    public static void playerFished(ItemFishedEvent event) {

    }

    @SubscribeEvent
    public static void playerSleep(PlayerSleepInBedEvent event) {

    }

    @SubscribeEvent
    public static void playerSetSpawn(PlayerSetSpawnEvent event) {

    }

    @SubscribeEvent
    public static void playerBrewed(PlayerBrewedPotionEvent event) {

    }

    @SubscribeEvent
    public static void animalTame(AnimalTameEvent event) {

    }

    @SubscribeEvent
    public static void VillageSeige(VillageSiegeEvent event) {

    }

    @SubscribeEvent
    public static void GameRuleChange(GameRuleChangeEvent event) {

    }

    @SubscribeEvent
    public static void Explosion(ExplosionEvent event) {

    }
}
