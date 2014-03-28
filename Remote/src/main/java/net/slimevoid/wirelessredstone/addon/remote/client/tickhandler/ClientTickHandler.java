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
package net.slimevoid.wirelessredstone.addon.remote.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.slimevoid.wirelessredstone.addon.remote.core.WRemoteCore;
import net.slimevoid.wirelessredstone.addon.remote.inventory.WirelessRemoteDevice;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClientTickHandler {

    public static boolean mouseDown, wasMouseDown, remotePulsing;
    Minecraft             mc = FMLClientHandler.instance().getClient();

    public static void processRemote(World world, EntityPlayer entityplayer, GuiScreen gui, MovingObjectPosition mop) {
        if (world.isRemote) {
            if (WirelessRemoteDevice.remoteTransmitter != null && !mouseDown) {
                WirelessRemoteDevice.sendDeactivateRemote(world,
                                                          entityplayer);
            }

            if (mouseClicked()
                && WirelessRemoteDevice.remoteTransmitter == null
                && entityplayer.inventory.getCurrentItem() != null
                && entityplayer.inventory.getCurrentItem().getItem() == WRemoteCore.itemRemote
                && !entityplayer.isSneaking()) {
                WirelessRemoteDevice.activatePlayerWirelessRemote(world,
                                                                  entityplayer);
            }
        }
    }

    public static boolean mouseClicked() {
        return mouseDown && !wasMouseDown;
    }

    public static void checkMouseClicks() {
        wasMouseDown = mouseDown;
        mouseDown = Mouse.isButtonDown(1);
    }

    @SubscribeEvent
    public void tickStart(ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            checkMouseClicks();
        } else {
            if (mc.theWorld != null && mc.theWorld.isRemote) {
                processRemote(mc.theWorld,
                              mc.thePlayer,
                              mc.currentScreen,
                              mc.objectMouseOver);
            }
        }
    }

}
