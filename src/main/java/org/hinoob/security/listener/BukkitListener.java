package org.hinoob.security.listener;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

// Disable certain game mechanics if server is lagging
public class BukkitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonPush(BlockPistonExtendEvent event) {
        double tps = SpigotReflectionUtil.getTPS();
        if(tps < 14.0) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPiston(BlockRedstoneEvent event) {
        double tps = SpigotReflectionUtil.getTPS();
        if(tps < 14.0) {
            event.setNewCurrent(0);
        }
    }
}
