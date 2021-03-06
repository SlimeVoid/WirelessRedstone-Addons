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
package wirelessredstone.addon.remote.client.network.packets.executors;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wirelessredstone.addon.remote.core.lib.ItemLib;
import wirelessredstone.addon.remote.items.ItemRedstoneWirelessRemote;
import wirelessredstone.api.IDevicePacketExecutor;
import wirelessredstone.client.presentation.gui.GuiRedstoneWirelessDevice;
import wirelessredstone.network.packets.PacketWireless;
import wirelessredstone.network.packets.PacketWirelessDevice;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientRemoteChangeFreqExecutor implements IDevicePacketExecutor {

    @Override
    public void execute(PacketWireless p, World world, EntityPlayer entityplayer) {
        if (p instanceof PacketWirelessDevice) {
            Gui currentScreen = FMLClientHandler.instance().getClient().currentScreen;
            if (currentScreen instanceof GuiRedstoneWirelessDevice) {
                ItemStack heldItem = entityplayer.getHeldItem();
                if (ItemLib.isWirelessRemote(heldItem)) {
                    GuiRedstoneWirelessDevice gui = (GuiRedstoneWirelessDevice) currentScreen;
                    gui.setFreq(p.getFreq());
                    ((ItemRedstoneWirelessRemote) heldItem.getItem()).setFreq(heldItem,
                                                                              p.getFreq());
                    ((ItemRedstoneWirelessRemote) heldItem.getItem()).setState(heldItem,
                                                                               p.getState());
                    gui.refreshGui();
                }
            }
        }
    }
}