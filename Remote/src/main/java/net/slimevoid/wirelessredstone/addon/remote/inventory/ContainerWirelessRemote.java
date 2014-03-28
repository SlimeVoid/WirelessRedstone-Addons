package net.slimevoid.wirelessredstone.addon.remote.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.slimevoid.wirelessredstone.api.IWirelessDevice;
import net.slimevoid.wirelessredstone.inventory.ContainerRedstoneDevice;

public class ContainerWirelessRemote extends ContainerRedstoneDevice {

    public ContainerWirelessRemote(IWirelessDevice device) {
        super(device);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

}
