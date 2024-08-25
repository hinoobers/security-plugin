package org.hinoob.security.module.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientSteerBoat;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientSteerVehicle;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidSteer extends Module {

    public InvalidSteer(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE) {
            WrapperPlayClientSteerVehicle wrapper = new WrapperPlayClientSteerVehicle(event);

            float forward = wrapper.getForward();
            float sideways = wrapper.getSideways();

            if(forward != 0) {
                if(filter(forward)) {
                    return kick();
                }
            }

            if(sideways != 0) {
                if(filter(sideways)) {
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

    private boolean filter(float f) {
        return f < -0.98f || f > 0.98f;
    }

}
