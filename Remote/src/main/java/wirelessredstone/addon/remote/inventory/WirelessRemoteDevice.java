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
package wirelessredstone.addon.remote.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wirelessredstone.addon.remote.core.lib.ItemLib;
import wirelessredstone.addon.remote.items.ItemRedstoneWirelessRemote;
import wirelessredstone.addon.remote.network.packets.PacketRemoteCommands;
import wirelessredstone.addon.remote.overrides.RedstoneWirelessRemoteOverride;
import wirelessredstone.api.IWirelessDevice;
import wirelessredstone.data.WirelessCoordinates;
import wirelessredstone.device.WirelessTransmitterDevice;
import wirelessredstone.network.packets.PacketWirelessDevice;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Eurymachus
 * 
 */
public class WirelessRemoteDevice extends WirelessTransmitterDevice {

    @SideOnly(Side.CLIENT)
    public static WirelessRemoteDevice                          remoteTransmitter;

    public static HashMap<EntityLivingBase, IWirelessDevice>    remoteTransmitters;
    public static TreeMap<WirelessCoordinates, IWirelessDevice> remoteWirelessCoords;

    protected static List<RedstoneWirelessRemoteOverride>       overrides = new ArrayList<RedstoneWirelessRemoteOverride>();

    public WirelessRemoteDevice(World world, EntityLivingBase entity, ItemStack itemstack) {
        super(world, entity, itemstack);
    }

    /**
     * Adds a Remote override to the Remote.
     * 
     * @param override
     *            Remote override.
     */
    public static void addOverride(RedstoneWirelessRemoteOverride override) {
        overrides.add(override);
    }

    @Override
    public void activate(World world, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            super.activate(world,
                           entity);
        }
    }

    @Override
    public void deactivate(World world, Entity entity, boolean isForced) {
        if (entity instanceof EntityLivingBase) {
            super.deactivate(world,
                             entity,
                             false);
            if (!world.isRemote && isForced
                && remoteTransmitters.containsKey(entity)) {
                deactivateWirelessRemote(world,
                                         (EntityLivingBase) entity,
                                         ((EntityLivingBase) entity).getHeldItem());
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void activatePlayerWirelessRemote(World world, EntityLivingBase entityliving) {
        if (entityliving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityliving;
            if (remoteTransmitter != null) {
                boolean isHeld = remoteTransmitter.isBeingHeld(entityliving);
                if (isHeld) {
                    return;
                }
                deactivatePlayerWirelessRemote(world,
                                               entityplayer);
            }
            remoteTransmitter = new WirelessRemoteDevice(world, entityliving, entityliving.getHeldItem());
            remoteTransmitter.activate(world,
                                       entityplayer);
        }
    }

    @SideOnly(Side.CLIENT)
    public static boolean deactivatePlayerWirelessRemote(World world, EntityLivingBase entityliving) {
        if (entityliving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityliving;
            if (remoteTransmitter == null) {
                return false;
            } else {
                remoteTransmitter.deactivate(world,
                                             entityplayer,
                                             false);
                remoteTransmitter = null;
                return true;
            }
        }
        return false;
    }

    public static void activateWirelessRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        if (remoteTransmitters.containsKey(entityliving)) {
            IWirelessDevice remote = remoteTransmitters.get(entityliving);
            if (((WirelessRemoteDevice) remote).isBeingHeld(entityliving)) {
                return;
            }
            deactivateWirelessRemote(world,
                                     entityliving,
                                     itemstack);
        }
        WirelessRemoteDevice remoteTransmitter = new WirelessRemoteDevice(world, entityliving, itemstack);
        remoteTransmitters.put(entityliving,
                               remoteTransmitter);
        if (remoteTransmitters.containsKey(entityliving)) {
            remoteWirelessCoords.put(remoteTransmitter.getCoords(),
                                     remoteTransmitter);
            remoteTransmitter.activate(world,
                                       entityliving);
        }
    }

    public static boolean deactivateWirelessRemote(World world, EntityLivingBase entityliving, ItemStack itemstack) {
        if (remoteTransmitters.containsKey(entityliving)) {
            IWirelessDevice remote = remoteTransmitters.get(entityliving);
            if (remoteWirelessCoords.containsKey(remote.getCoords())) {
                remoteWirelessCoords.remove(remote.getCoords());
            }
            remoteTransmitters.remove(entityliving);
            remote.deactivate(world,
                              entityliving,
                              false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String getActivateCommand() {
        return PacketRemoteCommands.remoteCommands.activate.toString();
    }

    @Override
    protected String getDeactivateCommand() {
        return PacketRemoteCommands.remoteCommands.deactivate.toString();
    }

    @Override
    public String getInvName() {
        return ItemLib.REMOTE;
    }

    @Override
    public boolean isBeingHeld(EntityLivingBase entityliving) {
        ItemStack heldItem = entityliving.getHeldItem();
        if (ItemLib.isWirelessRemote(heldItem)) {
            return ((ItemRedstoneWirelessRemote) heldItem.getItem()).getFreq(heldItem).equals(this.freq);
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public static void sendDeactivateRemote(World world, EntityPlayer entityplayer) {
        PacketWirelessDevice packet = new PacketWirelessDevice();
        packet.setCommand(PacketRemoteCommands.remoteCommands.deactivate.toString());
        PacketDispatcher.sendPacketToServer(packet.getPacket());
    }
}
