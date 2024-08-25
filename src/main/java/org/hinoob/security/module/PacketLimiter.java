package org.hinoob.security.module;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.bukkit.Bukkit;
import org.hinoob.security.user.SUser;

public class PacketLimiter extends Module{

    public PacketLimiter(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        // ViaVersion has its own packet limiter built in
        return !Bukkit.getPluginManager().isPluginEnabled("ViaVersion");
    }

    @Override
    public void receive(PacketReceiveEvent event) {

    }

    @Override
    public void send(PacketSendEvent event) {

    }
}
