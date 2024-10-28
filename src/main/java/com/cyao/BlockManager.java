package com.cyao;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlockManager {
    public static final RegistryKey<ItemGroup> DYNAMITE_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(TooMuchTNTRevived.MOD_ID, "item_group"));
    public static final ItemGroup DYNAMITE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.TNT))
            .displayName(Text.translatable("itemGroup.tnt"))
            .build();
    public static final RegistryKey<ItemGroup> TNT_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(TooMuchTNTRevived.MOD_ID, "dynamite_group"));
    public static final ItemGroup TNT_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.DIAMOND_HOE))
            .displayName(Text.translatable("itemGroup.dynamite"))
            .build();

    public static Block register(Block block, Identifier id, RegistryKey<ItemGroup> group) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        Item item = Registry.register(Registries.ITEM, id, blockItem);

        ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> {
            itemGroup.add(item);
        });

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, TNT_ITEM_GROUP_KEY, TNT_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, DYNAMITE_ITEM_GROUP_KEY, DYNAMITE_ITEM_GROUP);
    }
}
