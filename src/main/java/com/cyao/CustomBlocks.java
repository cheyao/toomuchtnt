package com.cyao;

import com.cyao.tnt.block.TNTx5;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class CustomBlocks {
    public static final Identifier TNTx5_ID = Identifier.of(TooMuchTNTRevived.MOD_ID, "tnt_x5");
    public static final RegistryKey<Block> TNTx5_KEY = RegistryKey.of(RegistryKeys.BLOCK, TNTx5_ID);
    public static final Block TNTx5 = BlockManager.register(
            new TNTx5(AbstractBlock.Settings.copy(Blocks.TNT).registryKey(TNTx5_KEY)),
            TNTx5_ID, BlockManager.TNT_ITEM_GROUP_KEY);

    public static void initialize() {}
}
