package org.hinoob.security.module.impl.inventory;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientEditBook;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class BookCheck extends Module {

    public BookCheck(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.EDIT_BOOK) {
            WrapperPlayClientEditBook wrapper = new WrapperPlayClientEditBook(event);

            if(wrapper.getSlot() < 0 || wrapper.getSlot() > 8) {
                // wiki.vg says this is only hotbar
                return kick();
            }

            if(wrapper.getPages().size() > 200) {
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
