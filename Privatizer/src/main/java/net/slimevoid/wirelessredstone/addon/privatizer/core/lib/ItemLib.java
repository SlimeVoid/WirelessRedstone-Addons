package net.slimevoid.wirelessredstone.addon.privatizer.core.lib;

import net.minecraft.item.ItemStack;
import net.slimevoid.wirelessredstone.addon.privatizer.items.ItemEtherPrivatizer;
import net.slimevoid.wirelessredstone.core.lib.BlockLib;

public class ItemLib {

    public static final String DEVICE_PREFIX = BlockLib.WIRELESS_PREFIX
                                               + "device.";

    public static final String REMOTE        = DEVICE_PREFIX + "remote";

    public static boolean isEtherPrivatizer(ItemStack itemstack) {
        return itemstack != null
               && itemstack.getItem() instanceof ItemEtherPrivatizer;
    }

}
