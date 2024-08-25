package org.hinoob.security.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import org.hinoob.security.user.UserManager;

public class ConnectionListener extends PacketListenerAbstract {

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        UserManager.remove(event.getUser());
    }
}
