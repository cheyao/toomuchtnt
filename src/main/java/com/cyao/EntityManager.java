package com.cyao;

import com.cyao.client.renderer.TNTx5Renderer;
import com.cyao.tnt.block.TNTx5;
import com.cyao.tnt.entity.TNTx5Entity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityManager {
    public static final Identifier TNTx5_ENTITY_ID = Identifier.of(TooMuchTNTRevived.MOD_ID, "tnt_x5");
    public static final RegistryKey<EntityType<?>> TNTx5_ENTITY_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            TNTx5_ENTITY_ID
    );
    public static final EntityType<TNTx5Entity> TNTx5_ENTITY_TYPE = EntityType.Builder.<TNTx5Entity>create(TNTx5Entity::new, SpawnGroup.MISC)
            .build(TNTx5_ENTITY_KEY);

    public static void initialize() {
        Registry.register(Registries.ENTITY_TYPE, TNTx5_ENTITY_KEY, TNTx5_ENTITY_TYPE);
        EntityRendererRegistry.register(TNTx5_ENTITY_TYPE, TNTx5Renderer::new);
    }
}
