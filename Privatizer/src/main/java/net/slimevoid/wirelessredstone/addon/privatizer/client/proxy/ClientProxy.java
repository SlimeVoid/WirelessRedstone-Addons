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
package net.slimevoid.wirelessredstone.addon.privatizer.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.slimevoid.wirelessredstone.addon.privatizer.client.network.packets.executors.ClientRemoteChangeFreqExecutor;
import net.slimevoid.wirelessredstone.addon.privatizer.client.presentation.gui.GuiEtherPrivatizer;
import net.slimevoid.wirelessredstone.addon.privatizer.inventory.ContainerEtherPrivatizer;
import net.slimevoid.wirelessredstone.addon.privatizer.network.packets.PacketPrivatizerCommands;
import net.slimevoid.wirelessredstone.addon.privatizer.proxy.CommonProxy;
import net.slimevoid.wirelessredstone.core.WirelessRedstone;
import net.slimevoid.wirelessredstone.core.lib.GuiLib;
import net.slimevoid.wirelessredstone.network.packets.core.PacketIds;

/**
 * WRClientProxy class
 * 
 * Executes client specific code
 * 
 * @author Eurymachus
 * 
 */
public class ClientProxy extends CommonProxy {

    /**
     * Wireless Remote GUI
     */
    // public static GuiRedstoneWirelessRemote guiWirelessRemote;

    @Override
    public void init() {
        initGUIs();
        super.init();
    }

    /**
     * Initializes GUI objects.
     */
    public static void initGUIs() {
    }

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUIID_DEVICE) {
        	// TODO:: Add device data
            return new GuiEtherPrivatizer(world, player, new ContainerEtherPrivatizer(null));
        }
        return null;
    }

    @Override
    public String getMinecraftDir() {
        return Minecraft.getMinecraft().mcDataDir.getPath();
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
    }

    @Override
    public void initPacketHandlers() {
        super.initPacketHandlers();
        // ///////////////////
        // Client Handlers //
        // ///////////////////
        WirelessRedstone.handler.getPacketHandler(PacketIds.DEVICE).registerClientExecutor(PacketPrivatizerCommands.remoteCommands.changeFreq.toString(),
                                                                                           new ClientRemoteChangeFreqExecutor());
    }
}
