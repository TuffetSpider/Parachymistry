package net.tuffet.parachymistry;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.component.ComponentType;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.component.TinctureIngredientComponent;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.gui.ModGuis;
import net.tuffet.parachymistry.item.ModItemGroups;
import net.tuffet.parachymistry.item.ModItems;
import net.tuffet.parachymistry.recipe.AlchymyRecipe;
import net.tuffet.parachymistry.recipe.ModRecipes;
import net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Parachymistry implements ModInitializer {

	public static final String MOD_ID = "parachymistry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final GameRules.Key<GameRules.BooleanRule> SHOULD_HAVE_DAMAGING_VIALS =
			GameRuleRegistry.register("moreDamagingVials", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

	public static ComponentType<TinctureIngredientComponent> TINCTUREITEM = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(Parachymistry.MOD_ID, "tincture_item"),
			ComponentType.<TinctureIngredientComponent>builder().codec(TinctureIngredientComponent.CODEC).build()
	);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerModEffects();
		ModItemGroups.registerItemGroups();
		ModGuis.initialize();
		ModRecipes.ALCHYMY = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "alchymy"), new RecipeType<AlchymyRecipe>(){});
		ModRecipes.AlCHYMY_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "alchymy_serializer"), new AlchymyRecipe.Serializer());
		ModRecipes.TINCTURE = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "tincture"), new RecipeType<MysteriousTinctureRecipe>(){});
		ModRecipes.TINCTURE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "tincture_serializer"), new MysteriousTinctureRecipe.Serializer());
	}
}