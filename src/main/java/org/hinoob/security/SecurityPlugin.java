package org.hinoob.security;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.security.listener.BukkitListener;
import org.hinoob.security.listener.ConnectionListener;
import org.hinoob.security.listener.PacketListener;

public class SecurityPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();

        PacketEvents.getAPI().getEventManager().registerListener(new ConnectionListener());
        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener());
        getServer().getPluginManager().registerEvents(new BukkitListener(), this);

        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
