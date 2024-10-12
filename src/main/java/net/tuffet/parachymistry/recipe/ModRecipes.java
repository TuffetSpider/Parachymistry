package net.tuffet.parachymistry.recipe;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

public class ModRecipes implements ModInitializer{

    public static RecipeType<AlchymyRecipe> ALCHYMY;
    public static RecipeSerializer<AlchymyRecipe> AlCHYMYSERIALIZER;




    @Override
    public void onInitialize() {
        ALCHYMY = Registry.register(Registries.RECIPE_TYPE, Identifier.of(Parachymistry.MOD_ID, "alchymy"), new RecipeType<AlchymyRecipe>(){});
        AlCHYMYSERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Parachymistry.MOD_ID, "alchymy_craft"), new AlchymyRecipe.Serializer());

    }
}