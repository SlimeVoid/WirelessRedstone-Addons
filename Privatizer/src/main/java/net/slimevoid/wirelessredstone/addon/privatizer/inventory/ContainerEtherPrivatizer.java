package net.slimevoid.wirelessredstone.addon.privatizer.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.slimevoid.wirelessredstone.api.IWirelessDevice;
import net.slimevoid.wirelessredstone.inventory.ContainerRedstoneDevice;

public class ContainerEtherPrivatizer extends ContainerRedstoneDevice {

    public ContainerEtherPrivatizer(IWirelessDevice device) {
        super(device);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

}
