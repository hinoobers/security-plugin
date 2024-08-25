package org.hinoob.security.module;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDisconnect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.hinoob.security.user.SUser;

public abstract class Module {

    protected final SUser user;

    public Module(SUser user) {
        this.user = user;
    }

    public boolean kick() {
        if(user.getConnectionState() == ConnectionState.LOGIN) {
            try {
                user.sendPacket(new WrapperPlayServerDisconnect(Component.text("You have been kicked.").color(TextColor.color(255,0,0))));
            } finally {
                user.close();
            }
        } else if(user.getConnectionState() == ConnectionState.PLAY) {
            try {
                user.sendPacket(new WrapperPlayServerDisconnect(Component.text("You have been kicked.").color(TextColor.color(255,0,0))));
            } finally {
                user.close();
            }
        } else {
            user.close();
        }

        return false;
    }

    public abstract boolean shouldBeEnabled();

    public abstract boolean receive(PacketReceiveEvent event);
    public abstract boolean send(PacketSendEvent event);
}
