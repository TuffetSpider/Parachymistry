package net.tuffet.parachymistry.recipe;

import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.tuffet.parachymistry.Parachymistry;

public final class ModRecipes{

    public static RecipeType<AlchymyRecipe> ALCHYMY;
    public static RecipeSerializer<AlchymyRecipe> AlCHYMY_SERIALIZER;
    public static RecipeType<MysteriousTinctureRecipe> TINCTURE;
    public static RecipeSerializer<MysteriousTinctureRecipe> TINCTURE_SERIALIZER;

    public static void init() {
        ModRecipes.ALCHYMY = Registry.register(Registries.RECIPE_TYPE, Parachymistry.id("alchymy"), new RecipeType<AlchymyRecipe>(){});
        ModRecipes.AlCHYMY_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Parachymistry.id("alchymy"), new AlchymyRecipe.Serializer());
        ModRecipes.TINCTURE = Registry.register(Registries.RECIPE_TYPE, Parachymistry.id("tincture"), new RecipeType<MysteriousTinctureRecipe>(){});
        ModRecipes.TINCTURE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Parachymistry.id("tincture"), new MysteriousTinctureRecipe.Serializer());
    }
}