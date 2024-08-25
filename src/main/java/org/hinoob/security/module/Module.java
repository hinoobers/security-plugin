package org.hinoob.security.module;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.hinoob.security.user.SUser;

public abstract class Module {

    protected final SUser user;

    public Module(SUser user) {
        this.user = user;
    }

    public abstract boolean shouldBeEnabled();

    public abstract void receive(PacketReceiveEvent event);
    public abstract void send(PacketSendEvent event);
}
