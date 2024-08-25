package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientUpdateSign;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidSign extends Module {

    public InvalidSign(SUser user) {
        super(user);
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.UPDATE_SIGN) {
            WrapperPlayClientUpdateSign wrapper = new WrapperPlayClientUpdateSign(event);

            // TODO: Check if the blockPosition is a sign
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }
}
