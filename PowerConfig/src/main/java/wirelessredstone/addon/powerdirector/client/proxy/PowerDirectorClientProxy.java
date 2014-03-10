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
package wirelessredstone.addon.powerdirector.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wirelessredstone.addon.powerdirector.client.presentation.gui.GuiRedstoneWirelessPowerDirector;
import wirelessredstone.addon.powerdirector.proxy.PowerDirectorCommonProxy;
import wirelessredstone.inventory.ContainerRedstoneWireless;
import wirelessredstone.tileentity.TileEntityRedstoneWireless;

/**
 * WRClientProxy class
 * 
 * Executes client specific code
 * 
 * @author Eurymachus
 * 
 */
public class PowerDirectorClientProxy extends PowerDirectorCommonProxy {

    /**
     * Power Configurator GUI
     */
    public static GuiRedstoneWirelessPowerDirector guiPowerD;

    @Override
    public void init() {
        initGUIs();
        super.init();
    }

    /**
     * Initializes GUI objects.
     */
    public static void initGUIs() {
        // guiPowerD = new GuiRedstoneWirelessPowerDirector();
        // TODO :: Overrides
    }

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(x,
                                                    y,
                                                    z);
        if (tileentity instanceof TileEntityRedstoneWireless) {
            // guiPowerD.assTileEntity((TileEntityRedstoneWireless) tileentity);
            return new GuiRedstoneWirelessPowerDirector(new ContainerRedstoneWireless((TileEntityRedstoneWireless) tileentity));// guiPowerD;
        }
        return null;
    }

    @Override
    public String getMinecraftDir() {
        return Minecraft.getMinecraft().mcDataDir.getPath();
    }

    @Override
    public void initPacketHandlers() {
        super.initPacketHandlers();
        // ///////////////////
        // Client Handlers //
        // ///////////////////
    }
}
