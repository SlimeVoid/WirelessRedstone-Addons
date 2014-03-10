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
package com.slimevoid.wirelessredstone.addon.remote.client.overrides;

import com.slimevoid.wirelessredstone.api.IGuiRedstoneWirelessDeviceOverride;
import com.slimevoid.wirelessredstone.api.IWirelessDevice;

import net.minecraft.world.World;

public class GuiRedstoneWirelessRemoteOverride implements
        IGuiRedstoneWirelessDeviceOverride {

    @Override
    public boolean beforeFrequencyChange(World world, IWirelessDevice device, Object oldFreq, Object newFreq) {
        return false;
    }
}
