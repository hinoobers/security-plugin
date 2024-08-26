package org.hinoob.security.module.impl.inventory;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPickItem;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidItem extends Module {

    public InvalidItem(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        System.out.println("Received packet " + event.getPacketType().getName());
        if(event.getPacketType() == PacketType.Play.Client.PICK_ITEM) {
            WrapperPlayClientPickItem wrapper = new WrapperPlayClientPickItem(event);

            System.out.println(wrapper.getSlot());
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }
}
