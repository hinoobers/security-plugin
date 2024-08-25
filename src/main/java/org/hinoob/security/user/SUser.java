package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.player.User;
import org.hinoob.security.module.Module;
import org.hinoob.security.module.impl.InvalidAttack;
import org.hinoob.security.module.impl.InvalidMovement;
import org.hinoob.security.module.impl.InvalidSign;
import org.hinoob.security.module.impl.PacketLimiter;

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

        modules.add(new InvalidMovement(this));
        modules.add(new InvalidAttack(this));
        modules.add(new PacketLimiter(this));
        modules.add(new InvalidSign(this));;
    }

    public Collection<Module> getModules() {
        return Collections.unmodifiableCollection(modules);
    }

    public UUID getUUID() {
        return user.getUUID();
    }

    public int getEntityId() {
        return user.getEntityId();
    }

    public void sendPacket(Object packet) {
        user.sendPacket(packet);
    }

    public void close() {
        user.closeConnection();
    }

    public ConnectionState getConnectionState() {
        return user.getConnectionState();
    }
}
