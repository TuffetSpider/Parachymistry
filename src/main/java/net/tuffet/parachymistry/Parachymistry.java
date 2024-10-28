package net.tuffet.parachymistry;


import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.ModGamerules.ModRules;
import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.component.ModComponents;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.item.ModItemGroups;
import net.tuffet.parachymistry.item.ModItems;
import net.tuffet.parachymistry.recipe.AlchymyRecipe;
import net.tuffet.parachymistry.recipe.ModRecipes;
import net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe;
import net.tuffet.parachymistry.tags.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Parachymistry implements ModInitializer {
	public static final String MOD_ID = "parachymistry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModEffects.registerModEffects();
		ModRecipes.ALCHYMY = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "alchymy"), new RecipeType<AlchymyRecipe>(){});
		ModRecipes.AlCHYMYSERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "alchymy_serializer"), new AlchymyRecipe.Serializer());
		ModRecipes.TINCTURE = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "tincture"), new RecipeType<MysteriousTinctureRecipe>(){});
		ModRecipes.TINCTURESERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "tincture_serializer"), new MysteriousTinctureRecipe.Serializer());
		ModComponents.intialize();
		ModTags.initialize();
		ModItemGroups.registerItemGroups();
		ModRules.initialize();



	}
}