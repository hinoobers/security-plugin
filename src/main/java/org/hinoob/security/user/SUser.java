package org.hinoob.security.user;

import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.player.User;
import org.hinoob.security.module.Module;
import org.hinoob.security.module.impl.*;
import org.hinoob.security.module.impl.block.InvalidSign;
import org.hinoob.security.module.impl.inventory.InvalidCreative;
import org.hinoob.security.module.impl.inventory.InvalidSlot;

import java.util.*;

public class SUser {

    private final User user;

    private final List<Module> modules = new ArrayList<>();

    public SUser(User user) {
        this.user = user;
        load();
    }

    private void load() {
        if(modules.isEmpty()) {
            modules.add(new Log4J(this));
            modules.add(new InvalidMovement(this));
            modules.add(new InvalidAttack(this));
            modules.add(new PacketLimiter(this));
            modules.add(new InvalidSettings(this));
            modules.add(new InvalidSteer(this));
            modules.add(new InvalidCreative(this));
            modules.add(new InvalidSlot(this));
            modules.add(new InvalidSign(this));
            modules.add(new Connection(this));
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public UUID getUUID() {
        return user.getUUID();
    }

    public String getName() {
        return user.getName();
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
