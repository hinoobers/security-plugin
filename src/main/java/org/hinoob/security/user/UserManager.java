package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.player.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static final Map<User, SUser> userMap = new HashMap<>();

    public static void create(User user) {
        userMap.put(user, new SUser(user));
    }

    public static SUser getUser(User user) {
        return userMap.getOrDefault(user, null);
    }

    public static int getSize() {
        return userMap.size();
    }

    public static void remove(User user) {
        userMap.remove(user);
    }
}
