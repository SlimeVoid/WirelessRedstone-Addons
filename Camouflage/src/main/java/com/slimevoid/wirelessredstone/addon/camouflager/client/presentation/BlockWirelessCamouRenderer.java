package com.slimevoid.wirelessredstone.addon.camouflager.client.presentation;

import com.slimevoid.wirelessredstone.addon.camouflager.core.lib.CamouLib;
import com.slimevoid.wirelessredstone.block.BlockRedstoneWireless;
import com.slimevoid.wirelessredstone.tileentity.TileEntityRedstoneWireless;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockWirelessCamouRenderer implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (block instanceof BlockRedstoneWireless) {
            TileEntity tileentity = world.getTileEntity(x,
                                                        y,
                                                        z);
            if (tileentity != null
                && tileentity instanceof TileEntityRedstoneWireless) {
                TileEntityRedstoneWireless tRW = (TileEntityRedstoneWireless) tileentity;

                Block blockRef = CamouLib.getBlock(world,
                                                   tRW);
                if (blockRef != null
                    && !(blockRef instanceof BlockRedstoneWireless)) {
                    renderer.renderBlockByRenderType(blockRef,
                                                     x,
                                                     y,
                                                     z);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelID) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 0;
    }

}
