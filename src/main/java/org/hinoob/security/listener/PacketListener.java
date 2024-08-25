package org.hinoob.security.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.hinoob.security.user.SUser;
import org.hinoob.security.user.UserManager;

public class PacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        SUser user = UserManager.getUser(event.getUser());

    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        SUser user = UserManager.getUser(event.getUser());

    }
}
