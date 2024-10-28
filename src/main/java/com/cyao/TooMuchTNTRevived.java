package com.cyao;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TooMuchTNTRevived implements ModInitializer {
	public static final String MOD_ID = "too-much-tnt-revived";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Mod");
		Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "impact"),
				SoundEvent.of(Identifier.of(MOD_ID, "impact")));

		ItemManager.initialize();
		BlockManager.initialize(); // TF we need this? C++ is cleary superior
		EntityManager.initialize();

		CustomBlocks.initialize();

		LOGGER.info("Finished Initialization");
	}
}