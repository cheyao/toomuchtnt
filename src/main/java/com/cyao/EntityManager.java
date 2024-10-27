package com.cyao;

import com.cyao.tnt.TNTx5Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityManager {
    public static final RegistryKey<EntityType<?>> TNTx5_ID =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TooMuchTNTRevived.MOD_ID, "tnt_x5"));
    public static final EntityType<TNTx5Entity> TNTx5Entity =  Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(TooMuchTNTRevived.MOD_ID, "tnt_x5"),
            EntityType.Builder.create(TNTx5Entity::new, SpawnGroup.MISC).dropsNothing().makeFireImmune().dimensions(0.98f, 0.98f).eyeHeight(0.15F).maxTrackingRange(10).trackingTickInterval(10).build(TNTx5_ID)
    );

    public static void initialize() {}
}
