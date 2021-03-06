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
package wirelessredstone.addon.remote.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import wirelessredstone.addon.remote.core.lib.ItemLib;
import wirelessredstone.addon.remote.items.ItemRedstoneWirelessRemote;
import wirelessredstone.core.WRCore;
import wirelessredstone.core.lib.ConfigurationLib;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WRemoteCore {
    public static boolean isLoaded        = false;
    public static Item    itemRemote;
    public static int     remoteID        = 6245;

    @SideOnly(Side.CLIENT)
    public static boolean mouseDown, wasMouseDown, remotePulsing;

    public static long    pulseTime       = 2500;
    public static boolean duraTogg        = true;
    public static int     maxPulseThreads = 2;
    public static int     remoteoff       = 0;
    public static int     remoteon        = 1;

    /**
     * Fires off all the canons.<br>
     * Loads configurations and initializes objects. Loads ModLoader related
     * stuff.
     */
    public static boolean initialize() {

        loadConfig();

        WirelessRemote.proxy.init();

        WirelessRemote.proxy.initPacketHandlers();

        registerItems();

        WirelessRemote.proxy.addOverrides();

        WirelessRemote.proxy.registerRenderInformation();

        registerRecipes();

        return true;
    }

    /**
     * Loads configurations from the properties file.<br>
     * - Remote item ID: (Remote.ID)<br>
     */
    private static void loadConfig() {
        Configuration wirelessconfig = ConfigurationLib.getConfig();

        wirelessconfig.load();

        remoteID = wirelessconfig.get(Configuration.CATEGORY_ITEM,
                                      ItemLib.REMOTE,
                                      remoteID).getInt();
        duraTogg = wirelessconfig.get(Configuration.CATEGORY_GENERAL,
                                      "Duration Toggle",
                                      duraTogg).getBoolean(duraTogg);
        pulseTime = wirelessconfig.get(Configuration.CATEGORY_GENERAL,
                                       "Pulse Time",
                                       pulseTime).getInt();
        maxPulseThreads = wirelessconfig.get(Configuration.CATEGORY_GENERAL,
                                             "Max Threads",
                                             maxPulseThreads).getInt();
        wirelessconfig.save();
    }

    /**
     * Initializes Item objects.
     */
    private static void registerItems() {
        itemRemote = (new ItemRedstoneWirelessRemote(remoteID)).setUnlocalizedName(ItemLib.REMOTE);
    }

    /**
     * Registers the item names
     */
    // private static void registerItems() {
    // LanguageRegistry.addName( itemRemote,
    // "Wireless Remote");
    // LanguageRegistry.instance().addNameForObject( itemRemote,
    // "de_DE",
    // "Drahtloser Funkfernbedienung");
    // }

    /**
     * Registers recipes with ModLoader<br>
     * - Recipe for Remote.
     */
    private static void registerRecipes() {
        GameRegistry.addRecipe(new ItemStack(itemRemote, 1),
                               new Object[] {
                                       "I",
                                       "T",
                                       Character.valueOf('I'),
                                       Block.torchRedstoneActive,
                                       Character.valueOf('T'),
                                       WRCore.blockWirelessT });
    }
}
