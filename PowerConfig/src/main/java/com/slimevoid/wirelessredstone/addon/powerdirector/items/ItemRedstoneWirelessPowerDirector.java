/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * Lesser General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>
 */
package com.slimevoid.wirelessredstone.addon.powerdirector.items;

import com.slimevoid.wirelessredstone.addon.powerdirector.core.PowerDirector;
import com.slimevoid.wirelessredstone.core.WRCore;
import com.slimevoid.wirelessredstone.core.lib.GuiLib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemRedstoneWirelessPowerDirector extends Item {

    public ItemRedstoneWirelessPowerDirector(int i) {
        super();
        setCreativeTab(WRCore.wirelessRedstone);
        maxStackSize = 1;
        setMaxDamage(64);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
        TileEntity tileentity = world.getTileEntity(i,
                                                    j,
                                                    k);

        if (tileentity != null) {
            entityplayer.openGui(PowerDirector.instance,
                                 GuiLib.GUIID_DEVICE,
                                 world,
                                 i,
                                 j,
                                 k);
            // TODO :: Open GUI
            itemstack.damageItem(1,
                                 entityplayer);
            return true;
        }
        return false;
    }

    public boolean isFull3D() {
        return true;
    }
}
