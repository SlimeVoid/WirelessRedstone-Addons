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
package net.slimevoid.wirelessredstone.addon.privatizer.core;

import net.slimevoid.wirelessredstone.addon.privatizer.core.lib.CoreLib;
import net.slimevoid.wirelessredstone.addon.privatizer.network.packets.PacketPrivatizerCommands;
import net.slimevoid.wirelessredstone.api.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Wireless Privatizer ModLoader initializing class.
 * 
 * @author Eurymachus
 */
@Mod(
        modid = CoreLib.MOD_ID,
        name = CoreLib.MOD_NAME,
        version = CoreLib.MOD_VERSION,
        dependencies = CoreLib.MOD_DEPENDENCIES)
/**
 * FML fascade class.
 * This class uses FML annotations and sorts initialization.
 * 
 * ConnectionHandler: 
 * ClientPacketHandler: 
 * ServerPacketHandler: 
 * 
 * @author Eurymachus
 */
public class EtherPrivatizer {

    @SidedProxy(
            clientSide = CoreLib.CLIENT_PROXY,
            serverSide = CoreLib.COMMON_PROXY)
    public static ICommonProxy proxy;

    @Instance(CoreLib.MOD_ID)
    public static EtherPrivatizer     instance;

    /**
     * Pre-initialization
     * 
     * @param event
     */
    @EventHandler
    public void EtherPrivatizerPreInit(FMLPreInitializationEvent event) {
        PrivatizerCore.preInitialize();
    }

    /**
     * Initialization
     * 
     * @param event
     */
    @EventHandler
    public void EtherPrivatizerInit(FMLInitializationEvent event) {
        PrivatizerCore.initialize();
        PacketPrivatizerCommands.registerCommands();
        EtherPrivatizer.proxy.initPacketHandlers();
    }

    /**
     * Post-initialization
     * 
     * @param event
     */
    @EventHandler
    public void EtherPrivatizerPostInit(FMLPostInitializationEvent event) {
    }
}
