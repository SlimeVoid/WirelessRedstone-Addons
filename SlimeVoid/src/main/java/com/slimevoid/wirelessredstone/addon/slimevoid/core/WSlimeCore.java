package com.slimevoid.wirelessredstone.addon.slimevoid.core;

import com.slimevoid.wirelessredstone.addon.slimevoid.overrides.PacketWirelessSlimeVoidOverride;
import com.slimevoid.wirelessredstone.addon.slimevoid.overrides.TileEntityRedstoneWirelessSlimevoidOverride;
import com.slimevoid.wirelessredstone.network.packets.PacketWireless;
import com.slimevoid.wirelessredstone.tileentity.TileEntityRedstoneWireless;

public class WSlimeCore {

    /**
     * Fires off all the canons.<br>
     * Loads configurations and initializes objects. Loads ModLoader related
     * stuff.
     */
    public static boolean initialize() {
        addOverrides();
        return true;
    }

    private static void addOverrides() {
        TileEntityRedstoneWirelessSlimevoidOverride tileOverride = new TileEntityRedstoneWirelessSlimevoidOverride();
        TileEntityRedstoneWireless.addOverride(tileOverride);
        PacketWirelessSlimeVoidOverride packetOverride = new PacketWirelessSlimeVoidOverride();
        PacketWireless.addOverride(packetOverride);
    }
}
