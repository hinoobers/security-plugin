package org.hinoob.security.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.event.UserLoginEvent;
import org.hinoob.security.user.UserManager;

public class ConnectionListener extends PacketListenerAbstract {

    @Override
    public void onUserLogin(UserLoginEvent event) {
        UserManager.create(event.getUser());
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        UserManager.remove(event.getUser());
    }
}
