

package net.tuffet.parachymistry.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import net.tuffet.parachymistry.block.ModBlocks;

import java.util.stream.Stream;


public class AlchymyRecipe implements Recipe<AlchymyRecipeInput> {
    final Ingredient base;
    final Ingredient reagent;
    final Ingredient catalyst;
    final ItemStack result;


    public AlchymyRecipe(Ingredient base, Ingredient reagent, Ingredient catalyst, ItemStack result) {
        this.base = base;
        this.reagent = reagent;
        this.catalyst = catalyst;
        this.result = result;
    }

    public boolean matches(AlchymyRecipeInput alchymyRecipeInput, World world) {
        return this.base.test(alchymyRecipeInput.base()) && this.reagent.test(alchymyRecipeInput.base()) && this.catalyst.test(alchymyRecipeInput.catalyst());
    }


    public ItemStack craft(AlchymyRecipeInput alchymyRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = alchymyRecipeInput.base().copyComponentsToNewStack(this.result.getItem(), this.result.getCount());
        itemStack.applyUnvalidatedChanges(this.result.getComponentChanges());
        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    public boolean testReagant(ItemStack stack) {
        return this.reagent.test(stack);
    }

    public boolean testCatalyst(ItemStack stack) {
        return this.catalyst.test(stack);
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.AlCHYMYSERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALCHYMY;
    }

    public boolean isEmpty() {
        return Stream.of(this.base, this.reagent, this.catalyst).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer implements RecipeSerializer<net.tuffet.parachymistry.recipe.AlchymyRecipe> {
        private static final MapCodec<net.tuffet.parachymistry.recipe.AlchymyRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter((recipe) -> {
                return recipe.base;
            }), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter((recipe) -> {
                return recipe.reagent;
            }), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter((recipe) -> {
                return recipe.catalyst;
            }), ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter((recipe) -> {
                return recipe.result;
            })).apply(instance, net.tuffet.parachymistry.recipe.AlchymyRecipe::new);
        });
        public static final PacketCodec<RegistryByteBuf, net.tuffet.parachymistry.recipe.AlchymyRecipe> PACKET_CODEC = PacketCodec.ofStatic(net.tuffet.parachymistry.recipe.AlchymyRecipe.Serializer::write, net.tuffet.parachymistry.recipe.AlchymyRecipe.Serializer::read);

        public Serializer() {
        }

        public MapCodec<net.tuffet.parachymistry.recipe.AlchymyRecipe> codec() {
            return CODEC;
        }

        public PacketCodec<RegistryByteBuf, net.tuffet.parachymistry.recipe.AlchymyRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static net.tuffet.parachymistry.recipe.AlchymyRecipe read(RegistryByteBuf buf) {
            Ingredient ingredient = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient2 = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient3 = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            ItemStack itemStack = (ItemStack)ItemStack.PACKET_CODEC.decode(buf);
            return new net.tuffet.parachymistry.recipe.AlchymyRecipe(ingredient, ingredient2, ingredient3, itemStack);
        }

        private static void write(RegistryByteBuf buf, net.tuffet.parachymistry.recipe.AlchymyRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.base);
            Ingredient.PACKET_CODEC.encode(buf, recipe.reagent);
            Ingredient.PACKET_CODEC.encode(buf, recipe.catalyst);
            ItemStack.PACKET_CODEC.encode(buf, recipe.result);
        }
    }
}

