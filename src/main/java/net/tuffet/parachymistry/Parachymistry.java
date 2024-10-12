package net.tuffet.parachymistry;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.gui.AlchymyScreen;
import net.tuffet.parachymistry.item.ModItems;
import net.tuffet.parachymistry.recipe.AlchymyRecipe;
import net.tuffet.parachymistry.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tuffet.parachymistry.gui.ModGuis.ALCHYMY;

public class Parachymistry implements ModInitializer {
	public static final String MOD_ID = "parachymistry";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModEffects.registerModEffects();
		HandledScreens.register(ALCHYMY, AlchymyScreen::new);
		ModRecipes.ALCHYMY = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "alchymy"), new RecipeType<AlchymyRecipe>(){});
		ModRecipes.AlCHYMYSERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "alchymy_craft"), new AlchymyRecipe.Serializer());




	}
}