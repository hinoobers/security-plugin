package org.hinoob.security.module.impl.block;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientUpdateSign;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidSign extends Module {

    public InvalidSign(SUser user) {
        super(user);
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.UPDATE_SIGN) {
            WrapperPlayClientUpdateSign wrapper = new WrapperPlayClientUpdateSign(event);

            Player player = (Player) event.getPlayer();
            Material material = player.getWorld().getBlockAt(wrapper.getBlockPosition().getX(), wrapper.getBlockPosition().getY(), wrapper.getBlockPosition().getZ()).getType();
            if(!material.name().toLowerCase().contains("sign")) {
                return kick();
            }

            for(String line : wrapper.getTextLines()) {
                if(line.length() > 50) { // getting 47 with commas, don't know the actual limit
                    event.setCancelled(true);
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
