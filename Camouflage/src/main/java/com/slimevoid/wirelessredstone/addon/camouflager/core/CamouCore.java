package com.slimevoid.wirelessredstone.addon.camouflager.core;

import com.slimevoid.wirelessredstone.addon.camouflager.core.lib.IconLib;
import com.slimevoid.wirelessredstone.addon.camouflager.core.lib.ItemLib;
import com.slimevoid.wirelessredstone.addon.camouflager.items.ItemRedstoneWirelessCamouflager;
import com.slimevoid.wirelessredstone.core.lib.ConfigurationLib;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;

public class CamouCore {

    public static int  camouID = 6244;
    public static Item itemCamouflager;

    /**
     * Fires off all the canons.<br>
     * Loads configurations and initializes objects. Loads ModLoader related
     * stuff.
     */
    public static boolean initialize() {
        loadConfig();

        WirelessCamouflager.proxy.init();

        WirelessCamouflager.proxy.initPacketHandlers();

        initItems();

        WirelessCamouflager.proxy.addOverrides();

        WirelessCamouflager.proxy.registerRenderInformation();

        registerdRecipes();
        return true;
    }

    private static void loadConfig() {
        Configuration wirelessconfig = ConfigurationLib.getConfig();

        wirelessconfig.load();

        camouID = wirelessconfig.get(Configuration.CATEGORY_GENERAL,
                                     "Camouflager",
                                     camouID).getInt();

        wirelessconfig.save();
    }

    private static void initItems() {
        itemCamouflager = (new ItemRedstoneWirelessCamouflager(camouID)).setUnlocalizedName(ItemLib.CAMOUFLAGER).setTextureName(IconLib.CAMOUFLAGER);
        GameRegistry.registerItem(itemCamouflager,
                                  ItemLib.CAMOUFLAGER);
    }

    private static void registerdRecipes() {
        GameRegistry.addRecipe(new ItemStack(itemCamouflager, 1),
                               new Object[] {
                                       "GRG",
                                       "RXR",
                                       "RGR",
                                       Character.valueOf('G'),
                                       Blocks.glass,
                                       Character.valueOf('R'),
                                       Items.redstone,
                                       Character.valueOf('X'),
                                       Blocks.iron_block });
    }
}
