package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class Building extends Module {

    public Building(SUser user) {
        super(user);
    }

    private boolean startedDigging = false;

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {
            WrapperPlayClientPlayerBlockPlacement wrapper = new WrapperPlayClientPlayerBlockPlacement(event);

            if(wrapper.getSequence() < 0)
                return kick();
        } else if(event.getPacketType() == PacketType.Play.Client.PLAYER_DIGGING) {
            WrapperPlayClientPlayerDigging wrapper = new WrapperPlayClientPlayerDigging(event);

            if(wrapper.getSequence() < 0)
                return kick();
            if(wrapper.getAction() == DiggingAction.START_DIGGING) {
                startedDigging = true;
            } else if(wrapper.getAction() == DiggingAction.CANCELLED_DIGGING) {
                startedDigging = false;
            } else if(wrapper.getAction() == DiggingAction.FINISHED_DIGGING) {
                if(!startedDigging) {
                    event.setCancelled(true);
                }
            }
        }
        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }
}
