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
package net.slimevoid.wirelessredstone.addon.privatizer.client.overrides;

import net.minecraft.world.World;
import net.slimevoid.wirelessredstone.api.IGuiRedstoneWirelessDeviceOverride;
import net.slimevoid.wirelessredstone.api.IWirelessDevice;

public class GuiEtherPrivatizerOverride implements
        IGuiRedstoneWirelessDeviceOverride {

    @Override
    public boolean beforeFrequencyChange(World world, IWirelessDevice device, Object oldFreq, Object newFreq) {
        return false;
    }
}