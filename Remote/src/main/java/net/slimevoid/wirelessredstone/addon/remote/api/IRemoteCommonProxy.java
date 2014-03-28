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
package net.slimevoid.wirelessredstone.addon.remote.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slimevoid.wirelessredstone.api.ICommonProxy;

public interface IRemoteCommonProxy extends ICommonProxy {
    public void activateRemote(World world, EntityLivingBase entitylivingbase, ItemStack itemstack);

    public boolean deactivateRemote(World world, EntityLivingBase entitylivingbase, ItemStack itemstack);

    public boolean isRemoteOn(World world, EntityLivingBase entitylivingbase, String freq);
}