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
package net.slimevoid.wirelessredstone.addon.remote.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.slimevoid.wirelessredstone.addon.remote.client.network.packets.executors.ClientRemoteChangeFreqExecutor;
import net.slimevoid.wirelessredstone.addon.remote.client.presentation.gui.GuiRedstoneWirelessRemote;
import net.slimevoid.wirelessredstone.addon.remote.client.tickhandler.ClientTickHandler;
import net.slimevoid.wirelessredstone.addon.remote.inventory.ContainerWirelessRemote;
import net.slimevoid.wirelessredstone.addon.remote.inventory.WirelessRemoteDevice;
import net.slimevoid.wirelessredstone.addon.remote.network.packets.PacketRemoteCommands;
import net.slimevoid.wirelessredstone.addon.remote.proxy.WRemoteCommonProxy;
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
public class WRemoteClientProxy extends WRemoteCommonProxy {

    /**
     * Wireless Remote GUI
     */
    // public static GuiRedstoneWirelessRemote guiWirelessRemote;

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
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
            WirelessRemoteDevice remote = new WirelessRemoteDevice(world, player, player.getHeldItem());
            // guiWirelessRemote.assWirelessDevice(remote,
            // player);
            return new GuiRedstoneWirelessRemote(world, player, new ContainerWirelessRemote(remote));
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
        WirelessRedstone.handler.getPacketHandler(PacketIds.DEVICE).registerClientExecutor(PacketRemoteCommands.remoteCommands.changeFreq.toString(),
                                                                                           new ClientRemoteChangeFreqExecutor());
    }

    @Override
    public void activateRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        if (!world.isRemote) {
            super.activateRemote(world,
                                 entityliving,
                                 itemstack);
        }
    }

    @Override
    public boolean deactivateRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        if (!world.isRemote) {
            return super.deactivateRemote(world,
                                          entityliving,
                                          itemstack);
        }
        return WirelessRemoteDevice.deactivatePlayerWirelessRemote(world,
                                                                   entityliving);
    }

    @Override
    public boolean isRemoteOn(World world, EntityLivingBase entitylivingbase, String freq) {
        if (!world.isRemote) {
            return super.isRemoteOn(world,
                                    entitylivingbase,
                                    freq);
        }
        boolean flag = WirelessRemoteDevice.remoteTransmitter == null ? false : WirelessRemoteDevice.remoteTransmitter.getFreq() == freq;
        return flag;
    }
}
