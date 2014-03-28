package net.slimevoid.wirelessredstone.addon.remote.core.lib;

import net.minecraft.item.ItemStack;
import net.slimevoid.wirelessredstone.addon.remote.items.ItemRedstoneWirelessRemote;
import net.slimevoid.wirelessredstone.core.lib.BlockLib;

public class ItemLib {

    public static final String DEVICE_PREFIX = BlockLib.WIRELESS_PREFIX
                                               + "device.";

    public static final String REMOTE        = DEVICE_PREFIX + "remote";

    public static boolean isWirelessRemote(ItemStack itemstack) {
        return itemstack != null
               && itemstack.getItem() instanceof ItemRedstoneWirelessRemote;
    }

}
