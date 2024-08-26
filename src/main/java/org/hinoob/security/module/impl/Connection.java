package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientKeepAlive;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPong;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientWindowConfirmation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerKeepAlive;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPing;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowConfirmation;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

import java.util.ArrayList;
import java.util.List;

public class Connection extends Module {

    public Connection(SUser user) {
        super(user);
    }

    private final List<Long> keepAliveIds = new ArrayList<>();
    private final List<Integer> transactionIds = new ArrayList<>();

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            WrapperPlayClientKeepAlive wrapper = new WrapperPlayClientKeepAlive(event);

            if(!keepAliveIds.remove(wrapper.getId())) {
                return kick();
            }
        } else if(event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            WrapperPlayClientWindowConfirmation wrapper = new WrapperPlayClientWindowConfirmation(event);

            int i = (int)wrapper.getActionId();
            if(!transactionIds.remove((Integer) i)) {
                return kick();
            }
        } else if(event.getPacketType() == PacketType.Play.Client.PONG) {
            WrapperPlayClientPong wrapper = new WrapperPlayClientPong(event);

            if(!transactionIds.remove((Integer) wrapper.getId())) {
                return kick();
            }
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        if(event.getPacketType() == PacketType.Play.Server.WINDOW_CONFIRMATION) {
            WrapperPlayServerWindowConfirmation wrapper = new WrapperPlayServerWindowConfirmation(event);

            transactionIds.add((int)wrapper.getActionId());
        } else if(event.getPacketType() == PacketType.Play.Server.KEEP_ALIVE) {
            WrapperPlayServerKeepAlive wrapper = new WrapperPlayServerKeepAlive(event);

            keepAliveIds.add(wrapper.getId());
        } else if(event.getPacketType() == PacketType.Play.Server.PING) {
            WrapperPlayServerPing wrapper = new WrapperPlayServerPing(event);

            transactionIds.add(wrapper.getId());
        }
        return true;
    }
}
