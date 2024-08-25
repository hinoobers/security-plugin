package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidMovement extends Module {

    public InvalidMovement(SUser user) {
        super(user);
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerFlying(event);

            if(wrapper.hasRotationChanged() && Math.abs(wrapper.getLocation().getPitch()) > 90) {
                return kick();
            }

            if(wrapper.hasPositionChanged()) {
                boolean invalidX = Double.isNaN(wrapper.getLocation().getX()) || Double.isInfinite(wrapper.getLocation().getX());
                boolean invalidY = Double.isNaN(wrapper.getLocation().getY()) || Double.isInfinite(wrapper.getLocation().getY());
                boolean invalidZ = Double.isNaN(wrapper.getLocation().getZ()) || Double.isInfinite(wrapper.getLocation().getZ());

                if(invalidX || invalidY || invalidZ) {
                    return kick();
                }
            }
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }
}
