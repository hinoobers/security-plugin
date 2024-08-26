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

    private double lastX, lastY, lastZ;

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

                if(Math.abs(wrapper.getLocation().getX() - this.lastX) > 100 || Math.abs(wrapper.getLocation().getY() - this.lastY) > 100 || Math.abs(wrapper.getLocation().getZ() - this.lastZ) > 100) {
                    return kick();
                }

                this.lastX = wrapper.getLocation().getX();
                this.lastY = wrapper.getLocation().getY();
                this.lastZ = wrapper.getLocation().getZ();
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
