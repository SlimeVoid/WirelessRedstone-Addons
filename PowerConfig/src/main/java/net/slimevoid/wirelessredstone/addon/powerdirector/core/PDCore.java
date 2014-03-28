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
package net.slimevoid.wirelessredstone.addon.powerdirector.core;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.slimevoid.wirelessredstone.addon.powerdirector.core.lib.IconLib;
import net.slimevoid.wirelessredstone.addon.powerdirector.core.lib.ItemLib;
import net.slimevoid.wirelessredstone.addon.powerdirector.items.ItemRedstoneWirelessPowerDirector;
import net.slimevoid.wirelessredstone.core.WRCore;
import net.slimevoid.wirelessredstone.core.lib.ConfigurationLib;
import cpw.mods.fml.common.registry.GameRegistry;

public class PDCore {
    public static boolean isLoaded = false;
    public static Item    itemPowDir;
    public static int     pdID     = 6243;

    public static boolean preInitialize() {

        loadConfig();

        PowerDirector.proxy.init();

        registerItems();

        PowerDirector.proxy.addOverrides();

        PowerDirector.proxy.registerRenderInformation();

        registerRecipes();

        return true;
    }

    private static void loadConfig() {
        Configuration wirelessconfig = ConfigurationLib.getConfig();

        wirelessconfig.load();

        pdID = wirelessconfig.get(Configuration.CATEGORY_GENERAL,
                                  ItemLib.POWER_DIR,
                                  6243).getInt();

        wirelessconfig.save();
    }

    private static void registerItems() {
        itemPowDir = (new ItemRedstoneWirelessPowerDirector(pdID)).setUnlocalizedName(ItemLib.POWER_DIR).setTextureName(IconLib.POWER_DIR);
        GameRegistry.registerItem(itemPowDir,
                                  ItemLib.POWER_DIR);
    }

    private static void registerRecipes() {
        GameRegistry.addRecipe(new ItemStack(itemPowDir, 1),
                               new Object[] {
                                       "R R",
                                       " X ",
                                       "R R",
                                       Character.valueOf('X'),
                                       WRCore.blockWirelessR,
                                       Character.valueOf('R'),
                                       Items.redstone });
    }
}