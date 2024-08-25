package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.player.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static Map<User, SUser> userMap = new HashMap<>();

    public static SUser getUser(User user) {
        if(userMap.containsKey(user)) {
            return new SUser(user);
        } else {
            userMap.put(user, new SUser(user));
            return getUser(user);
        }
    }

    public static void remove(User user) {
        userMap.remove(user);
    }
}
