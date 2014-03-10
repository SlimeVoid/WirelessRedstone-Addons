package com.slimevoid.wirelessredstone.addon.powerdirector.network.packets.executors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.slimevoid.library.IPacketExecutor;
import com.slimevoid.library.network.PacketUpdate;
import com.slimevoid.wirelessredstone.addon.powerdirector.network.packets.PacketPowerDirectorCommands;
import com.slimevoid.wirelessredstone.network.packets.PacketWireless;
import com.slimevoid.wirelessredstone.tileentity.TileEntityRedstoneWirelessR;

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
