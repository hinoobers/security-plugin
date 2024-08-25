package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;
import org.hinoob.security.user.UserManager;
import org.hinoob.security.util.Counter;

public class PacketLimiter extends Module {

    public PacketLimiter(SUser user) {
        super(user);
    }

    private final Counter counter = new Counter();

    @Override
    public boolean receive(PacketReceiveEvent event) {
        counter.start();

        if(counter.hasReached(1000L)) {
            System.out.println("packets=" + counter.getCount());
            int packets = counter.getCount();
            if(packets > 2000) {
                return kick();
            }

            counter.reset();
        } else {
            counter.increment();
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }

    @Override
    public boolean shouldBeEnabled() {
        // ViaVersion has its own packet limiter built in
        //return !Bukkit.getPluginManager().isPluginEnabled("ViaVersion");
        return true;
    }

}
