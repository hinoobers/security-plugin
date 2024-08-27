package org.hinoob.security.module.impl.inventory;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidWindow extends Module {

    public InvalidWindow(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow wrapper = new WrapperPlayClientClickWindow(event);

            WrapperPlayClientClickWindow.WindowClickType windowClickType = wrapper.getWindowClickType();

            if((windowClickType == WrapperPlayClientClickWindow.WindowClickType.PICKUP || windowClickType == WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE) && wrapper.getWindowId() >= 0 && wrapper.getButton() < 0) {
                return kick();
            } else if(wrapper.getWindowId() >= 0 && windowClickType == WrapperPlayClientClickWindow.WindowClickType.SWAP && wrapper.getSlot() < 0) {
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
