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
package net.slimevoid.wirelessredstone.addon.privatizer.client.presentation.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.slimevoid.wirelessredstone.addon.privatizer.client.overrides.GuiEtherPrivatizerOverride;
import net.slimevoid.wirelessredstone.addon.privatizer.network.packets.PacketPrivatizerCommands;
import net.slimevoid.wirelessredstone.api.IGuiRedstoneWirelessDeviceOverride;
import net.slimevoid.wirelessredstone.client.presentation.gui.GuiRedstoneWirelessDevice;
import net.slimevoid.wirelessredstone.core.lib.GuiLib;
import net.slimevoid.wirelessredstone.inventory.ContainerRedstoneDevice;

public class GuiEtherPrivatizer extends GuiRedstoneWirelessDevice {

    public GuiEtherPrivatizer(World world, EntityPlayer entityplayer, ContainerRedstoneDevice device) {
        super(world, entityplayer, device);
        IGuiRedstoneWirelessDeviceOverride override = new GuiEtherPrivatizerOverride();
        this.addOverride(override);
    }

    @Override
    protected ResourceLocation getBackgroundImage() {
        return GuiLib.GUI_SMALL;
    }

    @Override
    protected String getCommand() {
        return PacketPrivatizerCommands.remoteCommands.changeFreq.toString();
    }
}
