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
package wirelessredstone.addon.powerdirector.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wirelessredstone.addon.powerdirector.core.PowerDirector;
import wirelessredstone.addon.powerdirector.network.packets.PacketPowerDirectorCommands;
import wirelessredstone.addon.powerdirector.network.packets.executors.PacketPowerDirectorSettingsExecutor;
import wirelessredstone.addon.powerdirector.overrides.BlockRedstoneWirelessROverridePC;
import wirelessredstone.api.ICommonProxy;
import wirelessredstone.core.WRCore;
import wirelessredstone.core.WirelessRedstone;
import wirelessredstone.core.lib.GuiLib;
import wirelessredstone.inventory.ContainerRedstoneWireless;
import wirelessredstone.network.packets.core.PacketIds;
import wirelessredstone.tileentity.TileEntityRedstoneWireless;
import cpw.mods.fml.common.network.NetworkRegistry;

public class PowerDirectorCommonProxy implements ICommonProxy {

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public void registerConfiguration(File configFile) {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUIID_DEVICE) {
            TileEntity tileentity = world.getTileEntity(x,
                                                        y,
                                                        z);
            if (tileentity != null
                && tileentity instanceof TileEntityRedstoneWireless) {
                return new ContainerRedstoneWireless((TileEntityRedstoneWireless) tileentity);
            }
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
        NetworkRegistry.INSTANCE.registerGuiHandler(PowerDirector.instance,
                                                    PowerDirector.proxy);
    }

    @Override
    public void initPacketHandlers() {
        // ///////////////////
        // Server Executor //
        // ///////////////////
        WirelessRedstone.handler.getPacketHandler(PacketIds.ADDON).registerServerExecutor(PacketPowerDirectorCommands.powerConfigCommands.setDirection.toString(),
                                                                                          new PacketPowerDirectorSettingsExecutor());
        WirelessRedstone.handler.getPacketHandler(PacketIds.ADDON).registerServerExecutor(PacketPowerDirectorCommands.powerConfigCommands.setInDirection.toString(),
                                                                                          new PacketPowerDirectorSettingsExecutor());
    }

    @Override
    public void addOverrides() {
        WRCore.addOverrideToReceiver(new BlockRedstoneWirelessROverridePC());
    }

    @Override
    public void login(INetHandler handler, NetworkManager manager) {
    }
}
