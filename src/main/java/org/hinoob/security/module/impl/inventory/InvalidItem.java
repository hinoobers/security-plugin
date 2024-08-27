package org.hinoob.security.module.impl.inventory;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCreativeInventoryAction;
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
        if(event.getPacketType() == PacketType.Play.Client.CREATIVE_INVENTORY_ACTION) {
            WrapperPlayClientCreativeInventoryAction wrapper = new WrapperPlayClientCreativeInventoryAction(event);

            if(wrapper.getItemStack().getNBT().getTags().size() > 50) {
                return kick();
            }
        }

        return true;
    }

    @Override
    public boolean send(PacketSendEvent event) {
        return true;
    }
}
