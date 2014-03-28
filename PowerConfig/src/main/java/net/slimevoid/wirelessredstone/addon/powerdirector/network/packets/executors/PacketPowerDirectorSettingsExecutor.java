package net.slimevoid.wirelessredstone.addon.powerdirector.network.packets.executors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.wirelessredstone.addon.powerdirector.network.packets.PacketPowerDirectorCommands;
import net.slimevoid.wirelessredstone.network.packets.PacketWireless;
import net.slimevoid.wirelessredstone.tileentity.TileEntityRedstoneWirelessR;

public class PacketPowerDirectorSettingsExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketWireless) {
            TileEntity tileentity = ((PacketWireless) packet).getTarget(world);
            if (tileentity != null
                && tileentity instanceof TileEntityRedstoneWirelessR) {
                TileEntityRedstoneWirelessR tR = (TileEntityRedstoneWirelessR) tileentity;
                if (packet.getCommand().equals(PacketPowerDirectorCommands.powerConfigCommands.setDirection.toString())) {
                    tR.flipPowerDirection(packet.side);
                } else {
                    tR.flipIndirectPower(packet.side);
                }
            }
        }
    }

}
