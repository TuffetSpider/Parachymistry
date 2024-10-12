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

    }
}