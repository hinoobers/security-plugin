package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientSettings;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;
import org.hinoob.security.util.LocaleUtil;

public class InvalidSettings extends Module {
    public InvalidSettings(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.CLIENT_SETTINGS) {
            WrapperPlayClientSettings wrapper = new WrapperPlayClientSettings(event);

            int viewDistance = wrapper.getViewDistance();
            if(viewDistance < 0 || viewDistance > 32) {
                return kick();
            }

            if(!LocaleUtil.exists(wrapper.getLocale().toLowerCase())) {
                return kick();
            }
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }
}
