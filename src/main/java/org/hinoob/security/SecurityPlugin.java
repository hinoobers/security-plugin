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

import java.io.File;
import java.net.URL;

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
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PacketEvents.getAPI().init();

        PacketEvents.getAPI().getEventManager().registerListener(new ConnectionListener());
        if(Configuration.ENABLED) {
            PacketEvents.getAPI().getEventManager().registerListener(new PacketListener());
            getServer().getPluginManager().registerEvents(new BukkitListener(), this);
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
