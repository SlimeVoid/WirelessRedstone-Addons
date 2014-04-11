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
package net.slimevoid.wirelessredstone.addon.privatizer.client.network.packets.executors;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.wirelessredstone.addon.privatizer.core.lib.ItemLib;
import net.slimevoid.wirelessredstone.addon.privatizer.items.ItemEtherPrivatizer;
import net.slimevoid.wirelessredstone.api.IDevicePacketExecutor;
import net.slimevoid.wirelessredstone.client.presentation.gui.GuiRedstoneWirelessDevice;
import net.slimevoid.wirelessredstone.network.packets.PacketWirelessDevice;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientRemoteChangeFreqExecutor implements IDevicePacketExecutor {

    @Override
    public void execute(PacketUpdate p, World world, EntityPlayer entityplayer) {
        if (p instanceof PacketWirelessDevice) {
            PacketWirelessDevice packet = (PacketWirelessDevice) p;
            Gui currentScreen = FMLClientHandler.instance().getClient().currentScreen;
            if (currentScreen instanceof GuiRedstoneWirelessDevice) {
                ItemStack heldItem = entityplayer.getHeldItem();
                if (ItemLib.isEtherPrivatizer(heldItem)) {
                    GuiRedstoneWirelessDevice gui = (GuiRedstoneWirelessDevice) currentScreen;
                    gui.setFreq(packet.getFreq());
                    ((ItemEtherPrivatizer) heldItem.getItem()).setFreq(heldItem,
                                                                              packet.getFreq());
                    ((ItemEtherPrivatizer) heldItem.getItem()).setState(heldItem,
                                                                               packet.getState());
                    gui.refreshGui();
                }
            }
        }
    }
}