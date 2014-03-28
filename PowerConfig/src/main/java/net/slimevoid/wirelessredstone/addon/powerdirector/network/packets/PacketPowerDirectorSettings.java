package net.slimevoid.wirelessredstone.addon.powerdirector.network.packets;

import net.slimevoid.wirelessredstone.network.packets.PacketWirelessAddon;
import net.slimevoid.wirelessredstone.tileentity.TileEntityRedstoneWireless;

public class PacketPowerDirectorSettings extends PacketWirelessAddon {

    public PacketPowerDirectorSettings() {
        super();
    }

    public PacketPowerDirectorSettings(String command, int side, TileEntityRedstoneWireless inventory) {
        this();
        this.setCommand(command);
        this.setPosition(inventory.xCoord,
                         inventory.yCoord,
                         inventory.zCoord,
                         side);
    }
}
