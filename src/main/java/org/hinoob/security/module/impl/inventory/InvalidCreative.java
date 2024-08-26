package org.hinoob.security.module.impl.inventory;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCreativeInventoryAction;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.hinoob.security.module.Module;
import org.hinoob.security.user.SUser;

public class InvalidCreative extends Module {


    public InvalidCreative(SUser user) {
        super(user);
    }

    @Override
    public boolean shouldBeEnabled() {
        return true;
    }

    @Override
    public boolean receive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.CREATIVE_INVENTORY_ACTION) {
            WrapperPlayClientCreativeInventoryAction wrapper = new WrapperPlayClientCreativeInventoryAction(event);

            Player player = (Player) event.getPlayer();
            if(player.getGameMode() != GameMode.CREATIVE) {
                player.sendMessage("You are not in creative mode " + player.getGameMode());
                return kick();
            }

            if((wrapper.getSlot() < 0 && wrapper.getSlot() != -999) || wrapper.getSlot() > 45) {
                player.sendMessage("Invalid slot" + wrapper.getSlot());
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
