package com.slimevoid.wirelessredstone.addon.remote.core.lib;

import com.slimevoid.wirelessredstone.addon.remote.items.ItemRedstoneWirelessRemote;
import com.slimevoid.wirelessredstone.core.lib.BlockLib;

import net.minecraft.item.ItemStack;

public class ItemLib {

    public static final String DEVICE_PREFIX = BlockLib.WIRELESS_PREFIX
                                               + "device.";

    public static final String REMOTE        = DEVICE_PREFIX + "remote";

    public static boolean isWirelessRemote(ItemStack itemstack) {
        return itemstack != null
               && itemstack.getItem() instanceof ItemRedstoneWirelessRemote;
    }

}
