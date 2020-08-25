package com.datmodder.datannouncements;

import com.datmodder.datannouncements.announcements.EventAnnouncement;
import com.datmodder.datannouncements.announcements.EventSubAnnouncement;
import com.datmodder.datannouncements.delayedevents.DelayedAnnouncementEvent;
import com.datmodder.datannouncements.announcements.RepeatAnnouncement;
import com.datmodder.datannouncements.announcements.YAMLFile;
import com.datmodder.datannouncements.enums.Events;
import com.demmodders.datmoddingapi.delayedexecution.DelayHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

@Mod(
        modid = DatAnnouncements.MOD_ID,
        name = DatAnnouncements.MOD_NAME,
        version = DatAnnouncements.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "required-after:datmoddingapi@[1.1.1,)"
)
public class DatAnnouncements {
    public static final String MOD_ID = "datannouncements";
    public static final String MOD_NAME = "Dat Announcements";
    public static final String VERSION = "1.0.0";
    public static final Logger LOG = LogManager.getLogger(DatAnnouncements.MOD_ID);
    public static File announceFilePath;

    public HashMap<Events, ArrayList<EventSubAnnouncement>> eventAnnouncments = new HashMap<>();

    // Needed so we can cancel them when reloading
    ArrayList<DelayedAnnouncementEvent> delayedEvents = new ArrayList<>();

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static DatAnnouncements INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        announceFilePath = new File(event.getModConfigurationDirectory(), DatAnnouncements.MOD_ID);
        if (!announceFilePath.exists()) {
            announceFilePath.mkdir();
        }
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        loadAnnouncements();
    }

    public void reloadAnnouncements() {
        for (DelayedAnnouncementEvent event : delayedEvents) {
            event.cancel();
        }
        delayedEvents.clear();
    }

    public void loadAnnouncements(){
        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        YAMLFile yaml;
        Yaml loader = new Yaml(options);

        File theYaml = new File(announceFilePath, "announcements.yaml");
        if (theYaml.exists()) {
            LOG.info("Loading Announcements");

            // Load Yaml
            try {
                FileReader reader = new FileReader(theYaml);
                yaml = loader.loadAs(reader, YAMLFile.class);

                if (!yaml.isEmpty()) {
                    // Repeats
                    for (RepeatAnnouncement announcement : yaml.repeatAnnoucements) {
                        DelayedAnnouncementEvent dEvent = new DelayedAnnouncementEvent(announcement.firstDelay, announcement.interval, announcement.message);
                        delayedEvents.add(dEvent);
                        DelayHandler.addEvent(dEvent);
                    }

                    // Events
                    for (EventAnnouncement announcement : yaml.eventAnnouncements) {
                        ArrayList<EventSubAnnouncement> array;
                        if (eventAnnouncments.containsKey(announcement.event)) {
                            array = eventAnnouncments.get(announcement.event);
                        } else {
                            array = new ArrayList<>();
                            eventAnnouncments.put(announcement.event, array);
                        }
                        array.add(new EventSubAnnouncement(announcement.message, announcement.target));
                    }
                } else {
                    LOG.warn("Announcements file doesn't contain any announcements");
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            LOG.info("No announcements file, creating example file at " + theYaml.toString());

            try (InputStream file = getClass().getClassLoader().getResourceAsStream("JSON/Commands.json")) {
                Files.copy(file, theYaml.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
      /*
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
      */
    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        /**
         * Listen for the register event for creating custom items
         */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
           /*
             event.getRegistry().register(new ItemBlock(Blocks.myBlock).setRegistryName(MOD_ID, "myBlock"));
             event.getRegistry().register(new MySpecialItem().setRegistryName(MOD_ID, "mySpecialItem"));
            */
        }

        /**
         * Listen for the register event for creating custom blocks
         */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
           /*
             event.getRegistry().register(new MySpecialBlock().setRegistryName(MOD_ID, "mySpecialBlock"));
            */
        }
    }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
}
