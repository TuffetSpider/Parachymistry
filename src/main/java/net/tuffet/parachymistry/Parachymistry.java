package net.tuffet.parachymistry;

import net.fabricmc.api.ModInitializer;

import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parachymistry implements ModInitializer {
	public static final String MOD_ID = "parachymistry";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();



	}
}