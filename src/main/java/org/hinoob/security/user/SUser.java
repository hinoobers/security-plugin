package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.player.User;

import java.util.UUID;

public class SUser {

    private final User user;

    public SUser(User user) {
        this.user = user;
    }

    public UUID getUUID() {
        return user.getUUID();
    }
}
