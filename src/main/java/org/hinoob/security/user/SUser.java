package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.player.User;
import org.hinoob.security.module.Module;
import org.hinoob.security.module.PacketLimiter;

import java.util.*;

public class SUser {

    private final User user;

    private final List<Module> modules = new ArrayList<>();

    public SUser(User user) {
        this.user = user;
        load();
    }

    private void load() {
        modules.clear();

        modules.add(new PacketLimiter(this));
    }

    public Collection<Module> getModules() {
        return Collections.unmodifiableCollection(modules);
    }

    public UUID getUUID() {
        return user.getUUID();
    }
}
