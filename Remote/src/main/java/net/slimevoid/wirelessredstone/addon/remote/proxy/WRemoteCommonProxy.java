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
package net.slimevoid.wirelessredstone.addon.remote.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.wirelessredstone.addon.remote.api.IRemoteCommonProxy;
import net.slimevoid.wirelessredstone.addon.remote.core.WirelessRemote;
import net.slimevoid.wirelessredstone.addon.remote.inventory.ContainerWirelessRemote;
import net.slimevoid.wirelessredstone.addon.remote.inventory.WirelessRemoteDevice;
import net.slimevoid.wirelessredstone.addon.remote.network.packets.PacketRemoteCommands;
import net.slimevoid.wirelessredstone.addon.remote.network.packets.executor.ReceiverChangeFreqExecutor;
import net.slimevoid.wirelessredstone.addon.remote.network.packets.executor.RemoteChangeFreqExecutor;
import net.slimevoid.wirelessredstone.addon.remote.network.packets.executor.RemoteDeactivateExecutor;
import net.slimevoid.wirelessredstone.addon.remote.overrides.RedstoneEtherOverrideRemote;
import net.slimevoid.wirelessredstone.api.IWirelessDevice;
import net.slimevoid.wirelessredstone.core.WirelessRedstone;
import net.slimevoid.wirelessredstone.core.lib.GuiLib;
import net.slimevoid.wirelessredstone.ether.RedstoneEther;
import net.slimevoid.wirelessredstone.network.packets.core.PacketIds;
import cpw.mods.fml.common.network.NetworkRegistry;

public class WRemoteCommonProxy implements IRemoteCommonProxy {

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public void registerConfiguration(File configFile) {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUIID_DEVICE) {
            WirelessRemoteDevice device = new WirelessRemoteDevice(world, player, player.getHeldItem());
            return new ContainerWirelessRemote(device);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public String getMinecraftDir() {
        return ".";
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {

    }

    @Override
    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(WirelessRemote.instance,
                                                    WirelessRemote.proxy);
        WirelessRemoteDevice.remoteTransmitters = new HashMap();
        WirelessRemoteDevice.remoteWirelessCoords = new TreeMap();
    }

    @Override
    public void initPacketHandlers() {
        // ///////////////////
        // Server Executor //
        // ///////////////////
        WirelessRedstone.handler.getPacketHandler(PacketIds.DEVICE).registerServerExecutor(PacketRemoteCommands.remoteCommands.changeFreq.toString(),
                                                                                           new RemoteChangeFreqExecutor());

        WirelessRedstone.handler.getPacketHandler(PacketIds.DEVICE).registerServerExecutor(PacketRemoteCommands.remoteCommands.deactivate.toString(),
                                                                                           new RemoteDeactivateExecutor());

        WirelessRedstone.handler.getPacketHandler(PacketIds.ETHER).registerServerExecutor(PacketRemoteCommands.remoteCommands.updateReceiver.toString(),
                                                                                          new ReceiverChangeFreqExecutor());
    }

    @Override
    public void activateRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        WirelessRemoteDevice.activateWirelessRemote(world,
                                                    entityliving,
                                                    itemstack);
    }

    @Override
    public boolean deactivateRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        return WirelessRemoteDevice.deactivateWirelessRemote(world,
                                                             entityliving,
                                                             itemstack);
    }

    @Override
    public void addOverrides() {
        RedstoneEther.getInstance().addOverride(new RedstoneEtherOverrideRemote());
    }

    @Override
    public boolean isRemoteOn(World world, EntityLivingBase entityplayer, String freq) {
        if (WirelessRemoteDevice.remoteTransmitters.containsKey(entityplayer)) {
            IWirelessDevice remote = WirelessRemoteDevice.remoteTransmitters.get(entityplayer);
            return remote.getFreq() == freq;
        }
        return false;
    }

    @Override
    public void login(INetHandler handler, NetworkManager manager) {
    }
}
