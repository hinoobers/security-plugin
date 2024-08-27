package org.hinoob.security;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.security.listener.BukkitListener;
import org.hinoob.security.listener.ConnectionListener;
import org.hinoob.security.listener.PacketListener;
import org.hinoob.security.util.Configuration;
import org.hinoob.security.util.FileDownloader;
import org.hinoob.security.util.FileUtil;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class SecurityPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        // getting NoClassDefFoundERrors, gotta figure something out
//        File pluginsFolder = new File(Bukkit.getWorldContainer(), "plugins");
//        if(!new File(pluginsFolder, "packetevents.jar").exists()) {
//            getLogger().info("PacketEvents is missing, downloading...");
//
//            FileDownloader.download("https://github.com/retrooper/packetevents/releases/download/v2.4.0/packetevents-spigot-2.4.0.jar", new File(pluginsFolder, "packetevents.jar"));
//            getLogger().info("Downloaded PacketEvents!, restarting..");
//            Bukkit.shutdown();
//            return;
//        }
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();

        getLogger().info("Loaded! (Waiting to enable)");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PacketEvents.getAPI().init();

        PacketEvents.getAPI().getEventManager().registerListener(new ConnectionListener());
        if(Configuration.ENABLED) {
            PacketEvents.getAPI().getEventManager().registerListener(new PacketListener());
            getServer().getPluginManager().registerEvents(new BukkitListener(), this);

            if(getConfig().getBoolean("clear-logs")) {
                getLogger().info("Clearing logs...");
                File logsFolder = new File(Bukkit.getWorldContainer(), "logs");
                DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                int log = 0;
                for(File file : logsFolder.listFiles()) {
                    if(file.getName().equals("latest.log")) continue;

                    String date = file.getName().replaceAll(".log.gz", ""); // 2024-01-01-1.log.gz
                    // remove -1 from the end
                    date = date.substring(0, date.length() - 2);
                    LocalDate logDate = LocalDate.parse(date, sdf);
                    Period period = Period.between(logDate, LocalDate.now());
                    if(period.getDays() > 3) {
                        FileUtil.delete(file);
                        ++log;
                    }
                }
                getLogger().info("Cleared " + log + " logs!");
            }
        } else {
            getLogger().warning("Plugin is disabled from config!");
        }

        Configuration.load(this);

        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
