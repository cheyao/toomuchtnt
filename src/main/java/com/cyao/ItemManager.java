package com.cyao;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemManager {
    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(TooMuchTNTRevived.MOD_ID, id);

        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {}
}
