package com.slimevoid.wirelessredstone.addon.remote.inventory;

import com.slimevoid.wirelessredstone.api.IWirelessDevice;
import com.slimevoid.wirelessredstone.inventory.ContainerRedstoneDevice;

import net.minecraft.entity.player.EntityPlayer;

public class ContainerWirelessRemote extends ContainerRedstoneDevice {

    public ContainerWirelessRemote(IWirelessDevice device) {
        super(device);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

}
